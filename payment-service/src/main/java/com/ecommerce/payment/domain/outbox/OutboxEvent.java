package com.ecommerce.payment.domain.outbox;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OutboxEvent {

  @EqualsAndHashCode.Include
  private UUID id;

  private String aggregateId;
  private AggregateType aggregateType;
  private EventType eventType;
  private String payload;
  private Instant createdAt;
  private boolean published;

}
