package com.ecommerce.product.infrastructure.messaging.producer;

import com.ecommerce.product.application.port.ProductEventPublisher;
import com.ecommerce.product.domain.model.OutboxEvent;
import com.ecommerce.product.infrastructure.messaging.topic.KafkaTopicsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventPublisherKafka implements ProductEventPublisher {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final KafkaTopicsProperties topics;

  public void publish(OutboxEvent outboxEvent) {

    // ProductCreatedEvent event = toProductCreatedEvent(product);

    var topic = switch (outboxEvent.getEventType()) {
      case PRODUCT_CREATED -> topics.productCreated();
      case PRODUCT_UPDATED -> topics.productUpdated();
      case PRODUCT_DELETED -> topics.productDeleted();
    };

    kafkaTemplate.send(topic,
        outboxEvent.getAggregateId(),
        outboxEvent.getPayload());

    log.info("Published {}: {}", outboxEvent.getEventType(), outboxEvent.getPayload());
  }

}