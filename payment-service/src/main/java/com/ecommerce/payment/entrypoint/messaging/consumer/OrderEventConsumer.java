package com.ecommerce.payment.entrypoint.messaging.consumer;

import com.ecommerce.payment.domain.model.Payment;
import com.ecommerce.payment.domain.model.PaymentStatus;
import com.ecommerce.payment.entrypoint.messaging.event.OrderCreatedEvent;
import com.ecommerce.payment.infrastructure.messaging.producer.PaymentEventPublisherKafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

  private final PaymentEventPublisherKafka publisherEvent;

  @KafkaListener(topics = "${app.kafka.topics.order.order-created}")
  public void handleCreate(OrderCreatedEvent event) {

    log.info("Received order created event: {}", event);

    PaymentStatus paymentStatus = simulatePayment(event);

    var paymentProcessed = Payment.builder()
        .orderId(event.getOrderId())
        .status(paymentStatus)
        .build();

    publisherEvent.publishPaymentProcessed(paymentProcessed);

  }

  private PaymentStatus simulatePayment(OrderCreatedEvent event) {

    try {
      log.info("Processing payment for order: {}", event);
      Thread.sleep(2_000); // simulate processing

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return Math.random() > 0.2 // 80% success rate
        ? PaymentStatus.SUCCESS
        : PaymentStatus.FAIL;
  }

}
