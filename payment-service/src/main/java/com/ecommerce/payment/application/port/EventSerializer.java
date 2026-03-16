package com.ecommerce.payment.application.port;

public interface EventSerializer {

  String serialize(Object event);

}
