package com.ecommerce.payment.application.port;


import com.ecommerce.payment.domain.outbox.OutboxEvent;

public interface PaymentEventPublisher {

  void publish(OutboxEvent outboxEvent);

}
