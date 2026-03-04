package com.ecommerce.payment.infrastructure.messaging.event;

import com.ecommerce.payment.domain.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PaymentProcessedEvent {

  private UUID paymentId;
  private UUID paymentOrderId;
  private PaymentStatus paymentStatus;

}
