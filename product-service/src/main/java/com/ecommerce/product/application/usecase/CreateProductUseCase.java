package com.ecommerce.product.application.usecase;

import com.ecommerce.product.application.dto.CreateProductRequest;
import com.ecommerce.product.application.dto.ProductCreatedResponse;
import com.ecommerce.product.application.event.ProductCreatedEvent;
import com.ecommerce.product.application.mapper.ProductMapperDTO;
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

import static com.ecommerce.product.application.mapper.ProductMapperEvent.toProductCreatedEvent;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {

  private final ProductRepository productRepository;
  private final OutboxRepository outboxRepository;
  private final EventSerializer eventSerializer;
  private final ProductMapperDTO mapper;

  @Transactional
  public ProductCreatedResponse execute(CreateProductRequest request) {

    Product product = mapper.toDomain(request);
    product.setCreatedAt(Instant.now());

    product = productRepository.save(product);

    saveAsOutboxEvent(product);

    return mapper.toCreationResponse(product);
  }

  private void saveAsOutboxEvent(Product product) {

    var productCreatedEvent = toProductCreatedEvent(product);
    var outboxEvent = buildOutboxEvent(productCreatedEvent);
    outboxRepository.save(outboxEvent);

  }

  private OutboxEvent buildOutboxEvent(ProductCreatedEvent productCreatedEvent) {

    String payload = eventSerializer.serialize(productCreatedEvent);

    return OutboxEvent.builder()
        .id(UUID.randomUUID())
        .aggregateId(productCreatedEvent.getProductId().toString())
        .aggregateType(AggregateType.PRODUCT)
        .eventType(EventType.PRODUCT_CREATED)
        .payload(payload)
        .createdAt(productCreatedEvent.getProductCreatedAt())
        .published(false)
        .build();

  }

}
