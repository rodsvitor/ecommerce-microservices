package com.ecommerce.payment.infrastructure.persistence.entity.repository;

import com.ecommerce.payment.domain.outbox.OutboxEvent;
import com.ecommerce.payment.domain.outbox.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {

  private final Set<OutboxEvent> events = new LinkedHashSet<>();

  @Override
  public void save(OutboxEvent outboxEvent) {

    events.add(outboxEvent);

    // TODO Implement ORM Save
//    var outboxDocument = mapperORM.toDocument(outboxEvent);
//
//    outboxDocument = mongoRepository.save(outboxDocument);
//
//    return mapperORM.toDomain(outboxDocument);
  }

  @Override
  public List<OutboxEvent> findByPublishedFalse() {
//    return mongoRepository.findByPublishedFalse()
//        .stream()
//        .map(mapperORM::toDomain)
//        .toList();

    // TODO Implement ORM List
    return events.stream()
        .filter(Predicate.not(OutboxEvent::isPublished))
        .toList();
  }
}
