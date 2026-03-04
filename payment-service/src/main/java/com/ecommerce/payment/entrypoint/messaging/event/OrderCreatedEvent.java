package com.ecommerce.payment.entrypoint.messaging.event;

import com.ecommerce.payment.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
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
