package com.ecommerce.order.entrypoint.messaging.mapper;

import com.ecommerce.order.entrypoint.messaging.event.ProductCreatedEvent;
import com.ecommerce.order.entrypoint.messaging.event.ProductUpdatedEvent;
import com.ecommerce.order.infrastructure.persistence.mongo.entity.ProductSnapshot;

public interface ProductMapperEvent {

  static ProductSnapshot updateSnapshot(ProductCreatedEvent product) {

    return ProductSnapshot.builder()
        .id(product.getProductId())
        .name(product.getProductName())
        .price(product.getProductPrice())
        .createdAt(product.getProductCreatedAt())
        .build();
  }

  static void updateSnapshot(ProductSnapshot snapshot, ProductUpdatedEvent event) {

    snapshot.setId(event.getProductId());
    snapshot.setName(event.getProductName());
    snapshot.setPrice(event.getProductPrice());
    snapshot.setUpdatedAt(event.getProductUpdatedAt());

  }

}
