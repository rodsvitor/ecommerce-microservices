package com.ecommerce.product.application.port;

import com.ecommerce.product.domain.model.Product;

public interface ProductEventPublisher {

  void publishProductCreated(Product productCreatedEvent);

  void publishProductUpdated(Product productCreatedEvent);

  void publishProductDeleted(Long id);

}
