package com.ecommerce.order.application.usecase;

import com.ecommerce.order.application.dto.request.CreateOrderRequest;
import com.ecommerce.order.application.dto.request.OrderItemRequest;
import com.ecommerce.order.application.dto.response.OrderResponse;
import com.ecommerce.order.application.event.OrderCreatedEvent;
import com.ecommerce.order.application.mapper.OrderMapperDTO;
import com.ecommerce.order.application.port.EventSerializer;
import com.ecommerce.order.domain.model.Order;
import com.ecommerce.order.domain.model.OrderItem;
import com.ecommerce.order.domain.model.OrderStatus;
import com.ecommerce.order.domain.outbox.OutboxEvent;
import com.ecommerce.order.domain.outbox.OutboxRepository;
import com.ecommerce.order.domain.repository.OrderRepository;
import com.ecommerce.order.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.ecommerce.order.application.mapper.OrderMapperEvent.toOrderCreatedEvent;
import static com.ecommerce.order.domain.outbox.AggregateType.ORDER;
import static com.ecommerce.order.domain.outbox.EventType.ORDER_CREATED;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final OutboxRepository outboxRepository;

  private final EventSerializer eventSerializer;
  private final OrderMapperDTO orderMapperDTO;

  @Transactional
  public OrderResponse execute(CreateOrderRequest request) {

    var order = buildOrder(request);
    order = orderRepository.save(order);

    saveAsOutboxEvent(order);

    return orderMapperDTO.toOrderCreatedResponse(order);

  }

  private void saveAsOutboxEvent(Order order) {

    var orderCreatedEvent = toOrderCreatedEvent(order);
    OutboxEvent outboxEvent = buildOutboxEvent(orderCreatedEvent);
    outboxRepository.save(outboxEvent);

  }

  private Order buildOrder(CreateOrderRequest request) {

    List<OrderItem> items = request.items()
        .stream()
        .map(this::buildOrderItem)
        .toList();

    BigDecimal totalAmount = items.stream()
        .map(OrderItem::getSubtotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return Order.builder()
        .id(UUID.randomUUID())
        .userId(request.userId())
        .items(items)
        .totalAmount(totalAmount)
        .status(OrderStatus.CREATED)
        .createdAt(Instant.now())
        .build();
  }

  private OrderItem buildOrderItem(OrderItemRequest item) {

    var product = productRepository.findById(item.productId())
        .orElseThrow(() -> new RuntimeException("Product not found: " + item.productId()));

    BigDecimal subtotal = product.getPrice()
        .multiply(new BigDecimal(item.quantity()));

    return OrderItem.builder()
        .productId(product.getId())
        .productName(product.getName())
        .productPrice(product.getPrice())
        .quantity(item.quantity())
        .subtotal(subtotal)
        .build();
  }


  private OutboxEvent buildOutboxEvent(OrderCreatedEvent orderCreatedEvent) {

    String payload = eventSerializer.serialize(orderCreatedEvent);

    return OutboxEvent.builder()
        .id(UUID.randomUUID())
        .aggregateId(orderCreatedEvent.getOrderId().toString())
        .aggregateType(ORDER)
        .eventType(ORDER_CREATED)
        .payload(payload)
        .createdAt(orderCreatedEvent.getOrderCreatedAt())
        .published(false)
        .build();

  }

}
