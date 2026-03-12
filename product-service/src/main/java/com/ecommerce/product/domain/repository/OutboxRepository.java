package com.ecommerce.product.domain.repository;

import com.ecommerce.product.domain.model.OutboxEvent;

import java.util.List;

public interface OutboxRepository {

  void save(OutboxEvent outboxEvent);

  List<OutboxEvent> findAllByPublishedFalse();

}
