package com.ecommerce.order.domain.repository;

import com.ecommerce.order.domain.model.Order;

public interface OrderRepository {

  Order save(Order order);

}
