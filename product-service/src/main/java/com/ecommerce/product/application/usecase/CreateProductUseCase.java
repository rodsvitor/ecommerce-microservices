package com.ecommerce.product.application.usecase;

import com.ecommerce.product.application.mapper.ProductMapperDTO;
import com.ecommerce.product.application.port.ProductEventPublisher;
import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {

  private final ProductRepository productRepository;
  private final ProductMapperDTO mapper;
  private final ProductEventPublisher eventPublisher;

  public ProductCreatedResponse execute(CreateProductRequest request) {

    Product product = mapper.toDomain(request);
    product.setCreatedAt(Instant.now());

    product = productRepository.save(product);
    eventPublisher.publishProductCreated(product);

    return mapper.toCreationResponse(product);
  }

  public record CreateProductRequest(String name, BigDecimal price) {}
  public record ProductCreatedResponse(Long id, String name, BigDecimal price) {}


}
