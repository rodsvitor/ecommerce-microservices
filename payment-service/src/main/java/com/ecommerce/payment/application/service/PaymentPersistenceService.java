package com.ecommerce.payment.application.service;

import com.ecommerce.payment.application.command.CreatePaymentCommand;
import com.ecommerce.payment.application.usecase.CreatePaymentUseCase;
import com.ecommerce.payment.application.usecase.ProcessEventUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentPersistenceService {

  private final CreatePaymentUseCase createPaymentUseCase;
  private final ProcessEventUseCase processEventUseCase;

  @Transactional
  public void savePaymentAndMarkEvent(
      CreatePaymentCommand paymentCommand,
      UUID eventId) {

    createPaymentUseCase.execute(paymentCommand);
    processEventUseCase.execute(eventId);

  }


}
