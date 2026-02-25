package com.ecommerce.product.application.usecase;

import com.ecommerce.product.application.mapper.ProductMapperDTO;
import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {

  private final ProductRepository productRepository;
  private final ProductMapperDTO mapper;

  public CreateProductResponse execute(CreateProductRequest request) {

    Product product = mapper.toDomain(request);

    product = productRepository.save(product);

    return mapper.toCreationResponse(product);

  }

  public record CreateProductRequest(String name, BigDecimal price) {}
  public record CreateProductResponse(Long id, String name, BigDecimal price) {}


}
