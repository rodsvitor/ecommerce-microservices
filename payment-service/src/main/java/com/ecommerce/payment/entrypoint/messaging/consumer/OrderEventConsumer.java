package com.ecommerce.payment.entrypoint.messaging.consumer;

import com.ecommerce.payment.application.command.CreatePaymentCommand;
import com.ecommerce.payment.application.command.ProcessPaymentCommand;
import com.ecommerce.payment.application.usecase.CreatePaymentUseCase;
import com.ecommerce.payment.application.usecase.ProcessPaymentUseCase;
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

  private final ProcessPaymentUseCase processPaymentUseCase;
  private final CreatePaymentUseCase createPaymentUseCase;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "${app.kafka.topics.order.order-created}")
  public void handleCreate(String payload) {

    OrderCreatedEvent event = getOrderCreatedEvent(payload);

    log.info("🔥 Received order created event: {}", event);

    var processPaymentCommand = ProcessPaymentCommand.builder()
        .userId(event.getOrderUserId())
        .orderId(event.getOrderId())
        .amount(event.getOrderTotalAmount())
        .build();

    var paymentStatus = processPaymentUseCase.execute(processPaymentCommand);

    var createPaymentCommand = CreatePaymentCommand.builder()
        .orderId(event.getOrderId())
        .status(paymentStatus)
        .amount(event.getOrderTotalAmount())
        .build();

    createPaymentUseCase.execute(createPaymentCommand);

  }

  private OrderCreatedEvent getOrderCreatedEvent(String payload) {

    try {
      return objectMapper.readValue(payload, OrderCreatedEvent.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize event", e);
    }

  }

}
