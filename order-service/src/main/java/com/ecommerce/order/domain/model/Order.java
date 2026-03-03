package com.ecommerce.order.domain.model;

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
public class Order {

  private UUID id;
  private UUID userId;
  private List<OrderItem> items;
  private BigDecimal totalAmount;
  private OrderStatus status;
  private Instant createdAt;

}