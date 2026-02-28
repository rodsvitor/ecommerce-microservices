package com.ecommerce.product.infrastructure.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {

  private Long productId;
  private String productName;
  private BigDecimal productPrice;
  private Instant productCreatedAt;

}
