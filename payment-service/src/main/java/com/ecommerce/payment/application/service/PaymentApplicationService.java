package com.ecommerce.payment.application.service;

import com.ecommerce.payment.application.command.CreatePaymentCommand;
import com.ecommerce.payment.application.command.ProcessPaymentCommand;
import com.ecommerce.payment.application.usecase.ProcessPaymentUseCase;
import com.ecommerce.payment.domain.payment.PaymentStatus;
import com.ecommerce.payment.entrypoint.messaging.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentApplicationService {

  private final ProcessPaymentUseCase processPaymentUseCase;
  private final PaymentPersistenceService paymentPersistenceService;

  public void handleOrderCreated(OrderCreatedEvent event) {

    var paymentStatus = processPaymentUseCase.execute(buildProcessPaymentCommand(event));

    paymentPersistenceService.savePaymentAndMarkEvent(
        buildCreatePaymentCommand(event, paymentStatus),
        event.getEventId());

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
