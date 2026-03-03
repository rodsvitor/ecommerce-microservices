package com.ecommerce.order.domain.repository;

import com.ecommerce.order.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {

  Optional<Product> findById(Long id);

}
