package com.ecommerce.payment.application.mapper;

import com.ecommerce.payment.application.event.PaymentCreatedEvent;
import com.ecommerce.payment.domain.model.Payment;


public interface PaymentMapperEvent {

  static PaymentCreatedEvent toPaymentCreatedEvent(Payment payment) {

    return PaymentCreatedEvent.builder()
        .paymentId(payment.getId())
        .paymentOrderId(payment.getOrderId())
        .paymentStatus(payment.getStatus())
        .paymentAmount(payment.getAmount())
        .paymentCreatedAt(payment.getCreatedAt())
        .build();

  }

}
