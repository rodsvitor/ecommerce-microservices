package com.ecommerce.order.infrastructure.persistence.mongo.repository.outbox;

import com.ecommerce.order.domain.outbox.OutboxEvent;
import com.ecommerce.order.domain.outbox.OutboxRepository;
import com.ecommerce.order.infrastructure.persistence.mongo.mapper.OutboxEventMapperORM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository  {

  private final OutboxMongoRepository mongoRepository;
  private final OutboxEventMapperORM mapperORM;

  @Override
  public void save(OutboxEvent outboxEvent) {

    var outboxDocument = mapperORM.toDocument(outboxEvent);

    mongoRepository.save(outboxDocument);

  }

  @Override
  public List<OutboxEvent> findByPublishedFalse() {
    return mongoRepository.findByPublishedFalse()
        .stream()
        .map(mapperORM::toDomain)
        .toList();
  }
}
