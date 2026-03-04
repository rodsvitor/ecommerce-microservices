package com.ecommerce.payment.infrastructure.messaging.mapper;

import com.ecommerce.payment.domain.model.Payment;
import com.ecommerce.payment.infrastructure.messaging.event.PaymentProcessedEvent;

public interface PaymentMapperEvent {

  static PaymentProcessedEvent toPaymentProcessedEvent(Payment payment) {
    return PaymentProcessedEvent.builder()
        .paymentOrderId(payment.getOrderId())
        .paymentStatus(payment.getStatus())
        .build();
  }


}
