package com.ecommerce.order.infrastructure.persistence.mongo.entity;

import com.ecommerce.order.domain.outbox.AggregateType;
import com.ecommerce.order.domain.outbox.EventType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Data
@Document(collection = "outbox_events")
public class OutboxEventDocument {

  @Id
  private UUID id;

  private String aggregateId;
  private AggregateType aggregateType;
  private EventType eventType;
  private String payload;
  private Instant createdAt;
  private boolean published;

}
