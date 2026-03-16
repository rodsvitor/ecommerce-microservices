package com.ecommerce.product.application.usecase;

import com.ecommerce.product.application.dto.UpdateProductRequest;
import com.ecommerce.product.application.dto.UpdatedProductResponse;
import com.ecommerce.product.application.event.ProductUpdatedEvent;
import com.ecommerce.product.application.mapper.ProductMapperDTO;
import com.ecommerce.product.application.mapper.ProductMapperEvent;
import com.ecommerce.product.application.port.EventSerializer;
import com.ecommerce.product.domain.model.AggregateType;
import com.ecommerce.product.domain.model.EventType;
import com.ecommerce.product.domain.model.OutboxEvent;
import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.repository.OutboxRepository;
import com.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateProductUseCase {

  private final ProductRepository productRepository;
  private final ProductMapperDTO productMapperDTO;
  private final OutboxRepository outboxRepository;
  private final EventSerializer eventSerializer;

  @Transactional
  public UpdatedProductResponse execute(UpdateProductRequest updateProductRequest) {

    Product product = productRepository.findById(updateProductRequest.id())
        .orElseThrow(() -> new RuntimeException("Product not found!"));

    product.setName(updateProductRequest.name());
    product.setPrice(updateProductRequest.price());
    product.setUpdatedAt(Instant.now());

    product = productRepository.save(product);

    saveAsOutboxEvent(product);

    return productMapperDTO.toUpdatedProductResponse(product);

  }

  private void saveAsOutboxEvent(Product product) {

    var productUpdatedEvent = ProductMapperEvent.toProductUpdatedEvent(product);
    var outboxEvent = buildOutboxEvent(productUpdatedEvent);
    outboxRepository.save(outboxEvent);

  }

  private OutboxEvent buildOutboxEvent(ProductUpdatedEvent productUpdatedEvent) {

    String payload = eventSerializer.serialize(productUpdatedEvent);

    return OutboxEvent.builder()
        .id(UUID.randomUUID())
        .aggregateId(productUpdatedEvent.getProductId().toString())
        .aggregateType(AggregateType.PRODUCT)
        .eventType(EventType.PRODUCT_UPDATED)
        .payload(payload)
        .createdAt(productUpdatedEvent.getProductUpdatedAt())
        .published(false)
        .build();

  }

}