package com.ecommerce.order.entrypoint.controller;

import com.ecommerce.order.infrastructure.persistence.mongo.entity.ProductSnapshot;
import com.ecommerce.order.infrastructure.persistence.mongo.repository.ProductMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductQueryController {

  private final ProductMongoRepository productMongoRepository;

  @GetMapping
  public ResponseEntity<List<ProductSnapshot>> getAllProducts() {
    return ResponseEntity.ok(productMongoRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductSnapshot> getProductById(@PathVariable  Long id) {
    return ResponseEntity.ok(
        productMongoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"))
    );
  }

}
