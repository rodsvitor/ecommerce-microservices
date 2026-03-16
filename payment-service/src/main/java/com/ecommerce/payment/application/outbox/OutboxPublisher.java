package com.ecommerce.payment.application.outbox;

import com.ecommerce.payment.application.port.PaymentEventPublisher;
import com.ecommerce.payment.domain.outbox.OutboxEvent;
import com.ecommerce.payment.domain.outbox.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxPublisher {

  private final OutboxRepository outboxRepository;
  private final PaymentEventPublisher paymentEventPublisher;

  @Scheduled(fixedDelay = 10_000)
  public void publishEvents() {

    var events = outboxRepository.findByPublishedFalse();

    for (OutboxEvent event : events) {

      try {
        paymentEventPublisher.publish(event);
        event.setPublished(true);
        outboxRepository.save(event);

        log.info("Outbox event published: {}", event.getId());

      } catch (Exception e) {
        log.error("Failed to publish event {}", event.getId(), e);
      }

    }

  }

}