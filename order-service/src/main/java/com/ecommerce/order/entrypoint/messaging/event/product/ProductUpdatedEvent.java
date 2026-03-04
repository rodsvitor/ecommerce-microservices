package com.ecommerce.order.entrypoint.messaging.event.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdatedEvent {

  private Long productId;
  private String productName;
  private BigDecimal productPrice;
  private Instant productUpdatedAt;

}
