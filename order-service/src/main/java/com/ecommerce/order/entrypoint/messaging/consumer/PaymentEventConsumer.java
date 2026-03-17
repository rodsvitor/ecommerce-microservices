package com.ecommerce.order.entrypoint.messaging.consumer;

import com.ecommerce.order.application.usecase.UpdateStatusOrderBasedOnProcessedPaymentUseCase;
import com.ecommerce.order.domain.model.Payment;
import com.ecommerce.order.entrypoint.messaging.event.payment.PaymentProcessedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventConsumer {

  private final UpdateStatusOrderBasedOnProcessedPaymentUseCase updateOrderStatusUseCase;
  private final EventDeserializer eventDeserializer;

  @KafkaListener(topics = "${app.kafka.topics.payment.payment-processed}")
  public void handlePaymentProcessed(String payload) {

    // TODO try to receive PaymentProcessedEvent directly by parameter

    var paymentEvent = eventDeserializer.getObject(payload, PaymentProcessedEvent.class);

    log.info("Received payment processed event: {}", paymentEvent);

    // TODO ADD command class here to deal with usecase
    var payment = Payment.builder()
        .orderId(paymentEvent.getPaymentOrderId())
        .status(paymentEvent.getPaymentStatus())
        .build();

    updateOrderStatusUseCase.execute(payment);

    // TODO Implement persistence for processed events
    // TODO and idempotency with exceptions for duplicate key

  }

}
