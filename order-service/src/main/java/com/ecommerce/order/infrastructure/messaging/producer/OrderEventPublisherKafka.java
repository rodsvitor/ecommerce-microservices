package com.ecommerce.order.infrastructure.messaging.producer;

import com.ecommerce.order.application.port.OrderEventPublisher;
import com.ecommerce.order.domain.outbox.OutboxEvent;
import com.ecommerce.order.entrypoint.messaging.topic.OrderTopicsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventPublisherKafka implements OrderEventPublisher {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final OrderTopicsProperties topics;

  @Override
  public void publish(OutboxEvent outboxEvent) {

    try {

      kafkaTemplate.send(
          topics.created(),
          outboxEvent.getAggregateId(),
          outboxEvent.getPayload()
      ).get();

    } catch (Exception e) {
      throw new RuntimeException("Failed to publish outbox event", e);
    }

    log.info("🚀🚀🚀 Published created order event: {}", outboxEvent.getPayload());

  }

}
