package com.ecommerce.product.application.port;

import com.ecommerce.product.domain.model.OutboxEvent;

public interface ProductEventPublisher {

  void publish(OutboxEvent outboxEvent);

}
