package com.ecommerce.order.application.port;

import com.ecommerce.order.domain.model.Order;

public interface OrderEventPublisher {

  void publishOrderCreated(Order orderCreated);

}