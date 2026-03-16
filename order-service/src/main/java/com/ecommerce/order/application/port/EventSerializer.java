package com.ecommerce.order.application.port;

public interface EventSerializer {

  String serialize(Object event);

}