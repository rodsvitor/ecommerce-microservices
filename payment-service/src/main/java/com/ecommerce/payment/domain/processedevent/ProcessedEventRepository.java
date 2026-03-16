package com.ecommerce.payment.domain.processedevent;

import java.util.UUID;

public interface ProcessedEventRepository {

  void save(ProcessedEvent processedEvent);

  boolean existsByEventId(UUID eventId);

}
