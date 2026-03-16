package com.ecommerce.order.entrypoint.messaging.event.payment;

import com.ecommerce.order.domain.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProcessedEvent {

  private UUID paymentId;
  private UUID paymentOrderId;
  private PaymentStatus paymentStatus;

}
