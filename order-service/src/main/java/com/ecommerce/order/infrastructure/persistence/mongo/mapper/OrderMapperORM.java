package com.ecommerce.order.infrastructure.persistence.mongo.mapper;

import com.ecommerce.order.domain.model.Order;
import com.ecommerce.order.infrastructure.persistence.mongo.entity.OrderDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapperORM {

  OrderDocument toDocument(Order order);

  Order toDomain(OrderDocument orderDocument);

}
