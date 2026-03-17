package com.ecommerce.payment.infrastructure.persistence.repository;

import com.ecommerce.payment.application.exception.DuplicateEventException;
import com.ecommerce.payment.domain.processedevent.ProcessedEvent;
import com.ecommerce.payment.domain.processedevent.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProcessedEventRepositoryImpl implements ProcessedEventRepository {

  private final Set<ProcessedEvent> events = new LinkedHashSet<>();

  @Override
  public void save(ProcessedEvent processedEvent) {

    try {
      events.add(processedEvent);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicateEventException(processedEvent.eventId());
    }
    // TODO Implement ORM Save
//    var outboxDocument = mapperORM.toDocument(processedEvent);
//
//    outboxDocument = mongoRepository.save(outboxDocument);
//
//    return mapperORM.toDomain(outboxDocument);
  }

  @Override
  public boolean existsByEventId(UUID eventId) {
    return events.contains(ProcessedEvent.of(eventId));
  }

}
