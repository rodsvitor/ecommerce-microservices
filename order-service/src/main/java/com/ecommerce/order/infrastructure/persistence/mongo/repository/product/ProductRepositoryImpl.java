package com.ecommerce.order.infrastructure.persistence.mongo.repository.product;

import com.ecommerce.order.domain.model.Product;
import com.ecommerce.order.domain.repository.ProductRepository;
import com.ecommerce.order.infrastructure.persistence.mongo.mapper.ProductMapperORM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductMongoRepository mongoRepository;
  private final ProductMapperORM mapper;

  @Override
  public Optional<Product> findById(Long id) {
    return mongoRepository.findById(id)
        .map(mapper::toDomain);
  }

}
