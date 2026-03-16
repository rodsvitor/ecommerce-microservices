package com.ecommerce.order.application.port;

import com.ecommerce.order.domain.outbox.OutboxEvent;

public interface OrderEventPublisher {

  void publish(OutboxEvent outboxEvent);

}