package com.ecommerce.order.application.outbox;

import com.ecommerce.order.application.port.OrderEventPublisher;
import com.ecommerce.order.domain.outbox.OutboxEvent;
import com.ecommerce.order.domain.outbox.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxPublisher {

  private final OutboxRepository outboxRepository;
  private final OrderEventPublisher orderEvent;

  @Scheduled(fixedDelay = 10_000)
  public void publishEvents() {

    // TODO study about need of transaction here.
    List<OutboxEvent> events = outboxRepository.findByPublishedFalse();

    for (OutboxEvent event : events) {
      orderEvent.publish(event);
      event.setPublished(true);
      outboxRepository.save(event);

      log.info("Outbox event published: {}", event.getId());
    }

  }

}
