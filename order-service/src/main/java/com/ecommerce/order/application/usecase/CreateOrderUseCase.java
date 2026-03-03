package com.ecommerce.order.application.usecase;

import com.ecommerce.order.application.dto.request.CreateOrderRequest;
import com.ecommerce.order.application.dto.request.OrderItemRequest;
import com.ecommerce.order.application.dto.response.OrderCreatedResponse;
import com.ecommerce.order.application.mapper.OrderMapperDTO;
import com.ecommerce.order.domain.model.Order;
import com.ecommerce.order.domain.model.OrderItem;
import com.ecommerce.order.domain.model.OrderStatus;
import com.ecommerce.order.domain.repository.OrderRepository;
import com.ecommerce.order.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final OrderMapperDTO orderMapperDTO;

  public OrderCreatedResponse execute(CreateOrderRequest request) {

    List<OrderItem> items = request.items()
        .stream()
        .map(this::buildOrderItem)
        .toList();

    var order = buildOrder(request, items);

    order = orderRepository.save(order);

    return orderMapperDTO.toOrderCreatedResponse(order);

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

  private static Order buildOrder(CreateOrderRequest request, List<OrderItem> items) {

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

}
