package com.ecommerce.order.domain.outbox;

import java.util.List;

public interface OutboxRepository {

  void save(OutboxEvent outboxEvent);

  List<OutboxEvent> findByPublishedFalse();

}
