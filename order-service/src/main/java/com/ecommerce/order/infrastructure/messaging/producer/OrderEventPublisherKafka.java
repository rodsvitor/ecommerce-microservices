package com.ecommerce.order.infrastructure.messaging.producer;

import com.ecommerce.order.application.port.OrderEventPublisher;
import com.ecommerce.order.domain.model.Order;
import com.ecommerce.order.entrypoint.messaging.topic.OrderTopicsProperties;
import com.ecommerce.order.infrastructure.messaging.mapper.OrderMapperEvent;
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
  public void publishOrderCreated(Order orderCreated) {

    var event = OrderMapperEvent.toOrderCreatedEvent(orderCreated);

    kafkaTemplate.send(topics.created(), event);
    log.info("Published created order event: {}", event);

  }

}
