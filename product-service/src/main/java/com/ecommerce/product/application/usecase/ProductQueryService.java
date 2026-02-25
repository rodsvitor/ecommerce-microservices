package com.ecommerce.product.application.usecase;

import com.ecommerce.product.application.mapper.ProductMapperDTO;
import com.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

  private final ProductRepository productRepository;
  private final ProductMapperDTO mapper;

  public List<QueriedProductResponse> listAll() {

    return productRepository.findAll()
        .stream()
        .map(mapper::toQueriedResponse)
        .toList();

  }

  public QueriedProductResponse findById(Long id) {

    return productRepository.findById(id)
        .map(mapper::toQueriedResponse)
        .orElseThrow(() -> new RuntimeException("Product not found"));

  }

  public record QueriedProductResponse(String name, BigDecimal price) {}

}
