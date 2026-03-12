package com.ecommerce.order.infrastructure.persistence.mongo.mapper;

import com.ecommerce.order.domain.outbox.OutboxEvent;
import com.ecommerce.order.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutboxEventMapperORM {

  OutboxEventDocument toDocument(OutboxEvent outboxEvent);

  OutboxEvent toDomain(OutboxEventDocument outboxEventDocument);

}
