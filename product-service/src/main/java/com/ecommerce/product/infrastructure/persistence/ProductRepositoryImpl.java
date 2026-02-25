package com.ecommerce.product.infrastructure.persistence;

import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.repository.ProductRepository;
import com.ecommerce.product.infrastructure.persistence.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductJpaRepository jpaRepository;
  private final ProductMapper mapper;

  @Override
  public Product save(Product product) {

    ProductEntity productEntity = mapper.toEntity(product);

    productEntity = jpaRepository.save(productEntity);

    return mapper.toDomain(productEntity);

  }

  @Override
  public Optional<Product> findById(Long id) {

    return jpaRepository.findById(id)
        .map(mapper::toDomain);

  }

  @Override
  public List<Product> findAll() {

    return jpaRepository.findAll()
        .stream()
        .map(mapper::toDomain)
        .toList();

  }
}
