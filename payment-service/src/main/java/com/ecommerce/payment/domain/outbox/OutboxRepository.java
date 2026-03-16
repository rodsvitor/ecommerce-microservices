package com.ecommerce.payment.domain.outbox;

import java.util.List;

public interface OutboxRepository {

  void save(OutboxEvent outboxEvent);

  List<OutboxEvent> findByPublishedFalse();

}
