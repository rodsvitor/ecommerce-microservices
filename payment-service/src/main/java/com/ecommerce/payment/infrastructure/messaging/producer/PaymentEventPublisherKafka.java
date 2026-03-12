package com.ecommerce.payment.infrastructure.messaging.producer;

import com.ecommerce.payment.application.port.PaymentEventPublisher;
import com.ecommerce.payment.domain.outbox.OutboxEvent;
import com.ecommerce.payment.infrastructure.messaging.topic.PaymentTopicsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventPublisherKafka implements PaymentEventPublisher {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final PaymentTopicsProperties paymentTopics;

  @Override
  public void publish(OutboxEvent outboxEvent) {

    kafkaTemplate.send(
        paymentTopics.paymentProcessed(),
        outboxEvent.getAggregateId(),
        outboxEvent.getPayload());

    log.info("🚀🚀🚀 Published payment processed event: {}", outboxEvent.getPayload());

  }
}
