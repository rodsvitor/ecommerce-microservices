package com.ecommerce.order.entrypoint.messaging.consumer;

import com.ecommerce.order.entrypoint.messaging.event.product.ProductCreatedEvent;
import com.ecommerce.order.entrypoint.messaging.event.product.ProductDeletedEvent;
import com.ecommerce.order.entrypoint.messaging.event.product.ProductUpdatedEvent;
import com.ecommerce.order.infrastructure.persistence.mongo.entity.ProductSnapshot;
import com.ecommerce.order.infrastructure.persistence.mongo.repository.product.ProductMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.ecommerce.order.entrypoint.messaging.mapper.ProductMapperEvent.updateSnapshot;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventConsumer {

  // TODO Add use cases for deal with product
  private final ProductMongoRepository repository;
  private final EventDeserializer deserializer;


  @KafkaListener(topics = "${app.kafka.topics.product.created}")
  public void handleCreate(String payload) {

    // TODO try to receive ProductCreatedEvent directly by parameter

    var event = deserializer.getObject(payload, ProductCreatedEvent.class);
    log.info("Received product created event: {}", event);

    ProductSnapshot snapshot = updateSnapshot(event);

    repository.save(snapshot);
//    ack.acknowledge();

    // TODO Implement persistence for processed events.
  }

  @KafkaListener(topics = "${app.kafka.topics.product.updated}")
  public void handleUpdate(String payload) {

    var event = deserializer.getObject(payload, ProductUpdatedEvent.class);
    log.info("Received product updated event: {}", event);

    repository.findById(event.getProductId())
        .ifPresent(snapshot -> {
          updateSnapshot(snapshot, event);
          repository.save(snapshot);
        });

  }

  @KafkaListener(topics = "${app.kafka.topics.product.deleted}")
  public void handleDelete(String payload) {

    var event = deserializer.getObject(payload, ProductDeletedEvent.class);

    log.info("Received product deleted event: {}", event);
    repository.deleteById(event.getProductId());

  }

}
