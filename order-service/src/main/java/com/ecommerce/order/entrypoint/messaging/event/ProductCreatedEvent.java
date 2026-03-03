package com.ecommerce.order.entrypoint.messaging.event;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreatedEvent {

  private Long productId;
  private String productName;
  private BigDecimal productPrice;
  private Instant productCreatedAt;

}
