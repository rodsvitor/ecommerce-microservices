package com.ecommerce.product.infrastructure.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class ProductUpdatedEvent {

  private Long productId;
  private String productName;
  private BigDecimal productPrice;
  private Instant productUpdatedAt;

}
