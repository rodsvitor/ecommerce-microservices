package com.ecommerce.order.infrastructure.messaging.event;

import com.ecommerce.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {

  private UUID orderId;
  private UUID orderUserId;
  private List<OrderItemEvent> orderItems;
  private BigDecimal orderTotalAmount;
  private OrderStatus orderStatus;
  private Instant orderCreatedAt;

}
