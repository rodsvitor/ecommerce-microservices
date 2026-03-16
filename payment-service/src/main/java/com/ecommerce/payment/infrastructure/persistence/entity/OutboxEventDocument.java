package com.ecommerce.payment.infrastructure.persistence.entity;

import com.ecommerce.payment.domain.outbox.AggregateType;
import com.ecommerce.payment.domain.outbox.EventType;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
//@Document(collection = "outbox_events")
public class OutboxEventDocument {

//  @Id
  private UUID id;

  private String aggregateId;
  private AggregateType aggregateType;
  private EventType eventType;
  private String payload;
  private Instant createdAt;
  private boolean published;

}
