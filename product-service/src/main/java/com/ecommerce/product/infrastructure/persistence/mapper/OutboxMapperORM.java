package com.ecommerce.product.infrastructure.persistence.mapper;


import com.ecommerce.product.domain.model.OutboxEvent;
import com.ecommerce.product.infrastructure.persistence.entity.OutboxEventEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutboxMapperORM {

  OutboxEvent toDomain(OutboxEventEntity outboxEventEntity);

  OutboxEventEntity toEntity(OutboxEvent outboxEvent);

}
