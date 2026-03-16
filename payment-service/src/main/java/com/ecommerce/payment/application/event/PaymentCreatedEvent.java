package com.ecommerce.payment.application.event;

import com.ecommerce.payment.domain.payment.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class PaymentCreatedEvent {

  private UUID paymentId;
  private UUID paymentOrderId;
  private BigDecimal paymentAmount;
  private PaymentStatus paymentStatus;
  private Instant paymentCreatedAt;

}
