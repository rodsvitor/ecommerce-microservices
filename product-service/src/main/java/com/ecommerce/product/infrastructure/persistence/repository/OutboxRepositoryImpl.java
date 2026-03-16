package com.ecommerce.product.infrastructure.persistence.repository;


import com.ecommerce.product.domain.model.OutboxEvent;
import com.ecommerce.product.domain.repository.OutboxRepository;
import com.ecommerce.product.infrastructure.persistence.entity.OutboxEventEntity;
import com.ecommerce.product.infrastructure.persistence.mapper.OutboxMapperORM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {

  private final OutboxJPARepository outboxJPARepository;
  private final OutboxMapperORM mapperORM;

  @Override
  public void save(OutboxEvent outboxEvent) {

    OutboxEventEntity outboxEntity = mapperORM.toEntity(outboxEvent);
    outboxJPARepository.save(outboxEntity);

  }

  @Override
  public List<OutboxEvent> findAllByPublishedFalse() {

    return outboxJPARepository.findAllByPublishedFalse()
        .stream()
        .map(mapperORM::toDomain)
        .toList();

  }

}
