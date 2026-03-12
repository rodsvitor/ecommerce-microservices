package com.ecommerce.payment.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class Payment {

  private UUID id;
  private UUID orderId;
  private BigDecimal amount;
  private PaymentStatus status;
  private Instant createdAt;

}
