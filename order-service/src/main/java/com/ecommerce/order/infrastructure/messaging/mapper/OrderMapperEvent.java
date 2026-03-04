package com.ecommerce.order.infrastructure.messaging.mapper;

import com.ecommerce.order.domain.model.Order;
import com.ecommerce.order.domain.model.OrderItem;
import com.ecommerce.order.infrastructure.messaging.event.OrderCreatedEvent;
import com.ecommerce.order.infrastructure.messaging.event.OrderItemEvent;

import java.util.List;

public interface OrderMapperEvent {

  static OrderCreatedEvent toOrderCreatedEvent(Order order) {

    return OrderCreatedEvent.builder()
        .orderId(order.getId())
        .orderUserId(order.getUserId())
        .orderItems(mapItemsEvent(order.getItems()))
        .orderTotalAmount(order.getTotalAmount())
        .orderStatus(order.getStatus())
        .orderCreatedAt(order.getCreatedAt())
        .build();

  }

  private static List<OrderItemEvent> mapItemsEvent(List<OrderItem> items) {

    return items.stream()
        .map(item ->
            OrderItemEvent.builder()
                .productId(item.getProductId())
                .productName(item.getProductName())
                .productPrice(item.getProductPrice())
                .quantity(item.getQuantity())
                .subtotal(item.getSubtotal())
                .build()
        )
        .toList();

  }

}
