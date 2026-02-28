package com.ecommerce.product.application.usecase;

import com.ecommerce.product.application.port.ProductEventPublisher;
import com.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase {

  private final ProductRepository productRepository;
  private final ProductEventPublisher eventPublisher;

  public void execute(Long id) {

    productRepository.deleteById(id);
    eventPublisher.publishProductDeleted(id);

  }

}