package com.ecommerce.product.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class OutboxEvent {

  private UUID id;
  private String aggregateId;
  private AggregateType aggregateType;
  private EventType eventType;
  private String payload;
  private Instant createdAt;
  private boolean published;

}
