package com.ecommerce.product.application.usecase;

import com.ecommerce.product.application.event.ProductDeletedEvent;
import com.ecommerce.product.application.port.EventSerializer;
import com.ecommerce.product.domain.model.AggregateType;
import com.ecommerce.product.domain.model.EventType;
import com.ecommerce.product.domain.model.OutboxEvent;
import com.ecommerce.product.domain.repository.OutboxRepository;
import com.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase {

  private final ProductRepository productRepository;
  private final OutboxRepository outboxRepository;
  private final EventSerializer eventSerializer;

  @Transactional
  public void execute(Long id) {

    productRepository.deleteById(id);
    saveAsOutboxEvent(id);

  }

  private void saveAsOutboxEvent(Long id) {

    var productDeletedEvent = new ProductDeletedEvent(id);
    var outboxEvent = buildOutboxEvent(productDeletedEvent);
    outboxRepository.save(outboxEvent);

  }

  private OutboxEvent buildOutboxEvent(ProductDeletedEvent productDeletedEvent) {

    String payload = eventSerializer.serialize(productDeletedEvent);

    return OutboxEvent.builder()
        .id(UUID.randomUUID())
        .aggregateId(productDeletedEvent.productId().toString())
        .aggregateType(AggregateType.PRODUCT)
        .eventType(EventType.PRODUCT_DELETED)
        .payload(payload)
        .createdAt(Instant.now())
        .published(false)
        .build();

  }

}