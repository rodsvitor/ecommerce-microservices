package com.ecommerce.payment.application.usecase;

import com.ecommerce.payment.domain.processedevent.ProcessedEvent;
import com.ecommerce.payment.domain.processedevent.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessEventUseCase {

  private final ProcessedEventRepository processedEventRepository;

  public void execute(UUID eventId) {

    ProcessedEvent processedEvent = new ProcessedEvent(
        eventId,
        Instant.now()
    );

    processedEventRepository.save(processedEvent);

  }

}
