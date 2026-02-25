package com.ecommerce.product.domain.repository;

import com.ecommerce.product.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  Product save(Product product);

  Optional<Product> findById(Long id);

  List<Product> findAll();

}
