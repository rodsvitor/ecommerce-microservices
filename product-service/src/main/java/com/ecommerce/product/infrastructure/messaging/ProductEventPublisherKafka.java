package com.ecommerce.product.infrastructure.messaging;

import com.ecommerce.product.application.port.ProductEventPublisher;
import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.infrastructure.messaging.event.ProductCreatedEvent;
import com.ecommerce.product.infrastructure.messaging.event.ProductDeletedEvent;
import com.ecommerce.product.infrastructure.messaging.event.ProductUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.ecommerce.product.infrastructure.messaging.mapper.ProductMapperEvent.toProductCreatedEvent;
import static com.ecommerce.product.infrastructure.messaging.mapper.ProductMapperEvent.toProductUpdatedEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventPublisherKafka implements ProductEventPublisher {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final KafkaTopicsProperties topics;

  public void publishProductCreated(Product product) {

    ProductCreatedEvent event = toProductCreatedEvent(product);

    kafkaTemplate.send(topics.productCreated(), event);
    log.info("Published created product: {}", event);
  }

  @Override
  public void publishProductUpdated(Product product) {

    ProductUpdatedEvent event = toProductUpdatedEvent(product);

    kafkaTemplate.send(topics.productUpdated(), event);
    log.info("Published updated product: {}", event);

  }

  @Override
  public void publishProductDeleted(Long id) {

    ProductDeletedEvent event = new ProductDeletedEvent(id);

    kafkaTemplate.send(topics.productDeleted(), event);
    log.info("Published deleted product: {}", event);

  }

}