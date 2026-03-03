package com.ecommerce.product.infrastructure.messaging.mapper;

import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.infrastructure.messaging.event.ProductCreatedEvent;
import com.ecommerce.product.infrastructure.messaging.event.ProductUpdatedEvent;

public interface ProductMapperEvent {

  static ProductCreatedEvent toProductCreatedEvent(Product product) {

    return ProductCreatedEvent.builder()
        .productId(product.getId())
        .productName(product.getName())
        .productPrice(product.getPrice())
        .productCreatedAt(product.getCreatedAt())
        .build();

  }

  static ProductUpdatedEvent toProductUpdatedEvent(Product product) {

    return ProductUpdatedEvent.builder()
        .productId(product.getId())
        .productName(product.getName())
        .productPrice(product.getPrice())
        .productUpdatedAt(product.getUpdatedAt())
        .build();

  }

}