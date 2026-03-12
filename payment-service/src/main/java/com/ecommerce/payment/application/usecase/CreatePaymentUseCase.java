package com.ecommerce.payment.application.usecase;

import com.ecommerce.payment.application.command.CreatePaymentCommand;
import com.ecommerce.payment.application.event.PaymentCreatedEvent;
import com.ecommerce.payment.application.mapper.PaymentMapper;
import com.ecommerce.payment.application.port.EventSerializer;
import com.ecommerce.payment.domain.model.Payment;
import com.ecommerce.payment.domain.outbox.OutboxEvent;
import com.ecommerce.payment.domain.outbox.OutboxRepository;
import com.ecommerce.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

import static com.ecommerce.payment.application.mapper.PaymentMapperEvent.toPaymentCreatedEvent;
import static com.ecommerce.payment.domain.outbox.AggregateType.PAYMENT;
import static com.ecommerce.payment.domain.outbox.EventType.ORDER_CREATED;

@Service
@RequiredArgsConstructor
public class CreatePaymentUseCase {

  private final PaymentRepository paymentRepository;
  private final OutboxRepository outboxRepository;

  private final EventSerializer eventSerializer;
  private final PaymentMapper paymentMapper;

  @Transactional
  public void execute(CreatePaymentCommand command) {

    Payment payment = paymentMapper.toDomain(command);
    payment.setId(UUID.randomUUID());
    payment.setCreatedAt(Instant.now());

    payment = paymentRepository.save(payment);
    saveAsOutboxEvent(payment);

  }

  private void saveAsOutboxEvent(Payment payment) {
    var paymentCreatedEvent = toPaymentCreatedEvent(payment);
    var outboxEvent = buildOutboxEvent(paymentCreatedEvent);
    outboxRepository.save(outboxEvent);
  }

  private OutboxEvent buildOutboxEvent(PaymentCreatedEvent paymentCreatedEvent) {

    String payload = eventSerializer.serialize(paymentCreatedEvent);

    return OutboxEvent.builder()
        .id(UUID.randomUUID())
        .aggregateId(paymentCreatedEvent.getPaymentId().toString())
        .aggregateType(PAYMENT)
        .eventType(ORDER_CREATED)
        .payload(payload)
        .createdAt(paymentCreatedEvent.getPaymentCreatedAt())
        .published(false)
        .build();

  }

}
