package com.ecommerce.payment.entrypoint.messaging.consumer;

import com.ecommerce.payment.application.service.PaymentApplicationService;
import com.ecommerce.payment.entrypoint.messaging.event.OrderCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

  private final ObjectMapper objectMapper;
  private final PaymentApplicationService paymentApplicationService;

  @KafkaListener(topics = "${app.kafka.topics.order.order-created}")
  public void handleCreate(String payload) {

    // TODO try to receive OrderCreatedEvent in parameter
    OrderCreatedEvent event = getOrderCreatedEvent(payload);

    log.info("🔥 Received order created event: {}", event);

    paymentApplicationService.handleOrderCreated(event);

  }

  private OrderCreatedEvent getOrderCreatedEvent(String payload) {

    try {
      return objectMapper.readValue(payload, OrderCreatedEvent.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize event", e);
    }

  }

}
