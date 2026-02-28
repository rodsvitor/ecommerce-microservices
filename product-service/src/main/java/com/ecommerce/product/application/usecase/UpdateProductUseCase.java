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
public class UpdateProductUseCase {

  private final ProductRepository productRepository;
  private final ProductMapperDTO productMapperDTO;
  private final ProductEventPublisher eventPublisher;

  public UpdatedProductResponse execute(UpdateProductRequest updateProductRequest) {

    Product product = productRepository.findById(updateProductRequest.id())
        .orElseThrow(() -> new RuntimeException("Product not found!"));

    product.setName(updateProductRequest.name);
    product.setPrice(updateProductRequest.price);
    product.setUpdatedAt(Instant.now());

    product = productRepository.save(product);

    eventPublisher.publishProductUpdated(product);

    return productMapperDTO.toUpdatedProductResponse(product);

  }

  public record UpdateProductRequest(Long id, String name, BigDecimal price) {}

  public record UpdatedProductResponse (Long id, String name, BigDecimal price) {}

}