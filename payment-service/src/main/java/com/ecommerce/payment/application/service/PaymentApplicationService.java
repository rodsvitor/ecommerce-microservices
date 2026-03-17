package com.ecommerce.payment.application.service;

import com.ecommerce.payment.application.command.CreatePaymentCommand;
import com.ecommerce.payment.application.command.ProcessPaymentCommand;
import com.ecommerce.payment.application.exception.DuplicateEventException;
import com.ecommerce.payment.application.usecase.ProcessPaymentUseCase;
import com.ecommerce.payment.domain.payment.PaymentStatus;
import com.ecommerce.payment.entrypoint.messaging.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentApplicationService {

  private final ProcessPaymentUseCase processPaymentUseCase;
  private final PaymentPersistenceService paymentPersistenceService;

  public void handleOrderCreated(OrderCreatedEvent event) {

    try {

      var paymentStatus = processPaymentUseCase.execute(buildProcessPaymentCommand(event));

      paymentPersistenceService.savePaymentAndMarkEvent(
          buildCreatePaymentCommand(event, paymentStatus),
          event.getEventId());

    } catch (DuplicateEventException e) {
      log.info("Event already processed: {}", event.getEventId());
    }

  }

  private static ProcessPaymentCommand buildProcessPaymentCommand(OrderCreatedEvent event) {
    return ProcessPaymentCommand.builder()
        .userId(event.getOrderUserId())
        .orderId(event.getOrderId())
        .amount(event.getOrderTotalAmount())
        .build();
  }

  private static CreatePaymentCommand buildCreatePaymentCommand(OrderCreatedEvent event, PaymentStatus paymentStatus) {
    return CreatePaymentCommand.builder()
        .orderId(event.getOrderId())
        .status(paymentStatus)
        .amount(event.getOrderTotalAmount())
        .build();
  }

}
