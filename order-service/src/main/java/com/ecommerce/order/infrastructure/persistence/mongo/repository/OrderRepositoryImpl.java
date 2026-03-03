package com.ecommerce.order.infrastructure.persistence.mongo.repository;

import com.ecommerce.order.domain.model.Order;
import com.ecommerce.order.domain.repository.OrderRepository;
import com.ecommerce.order.infrastructure.persistence.mongo.mapper.OrderMapperORM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderMongoRepository mongoRepository;
  private final OrderMapperORM mapperORM;

  @Override
  public Order save(Order order) {

    var orderDocument = mapperORM.toDocument(order);

    orderDocument = mongoRepository.save(orderDocument);

    return mapperORM.toDomain(orderDocument);

  }
}
