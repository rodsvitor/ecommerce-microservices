package com.ecommerce.product.application.outbox;

import com.ecommerce.product.application.port.ProductEventPublisher;
import com.ecommerce.product.domain.model.OutboxEvent;
import com.ecommerce.product.domain.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxPublisher {

  private final OutboxRepository outboxRepository;
  private final ProductEventPublisher productEventPublisher;

  @Scheduled(fixedDelay = 60_000)
  public void publishEvents() {

    var events = outboxRepository.findAllByPublishedFalse();

    for (OutboxEvent event : events) {

      productEventPublisher.publish(event);
      event.setPublished(true);
      outboxRepository.save(event);

    }

  }

}
