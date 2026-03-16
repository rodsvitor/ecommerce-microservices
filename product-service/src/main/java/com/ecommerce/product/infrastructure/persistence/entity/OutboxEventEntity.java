package com.ecommerce.product.infrastructure.persistence.entity;

import com.ecommerce.product.domain.model.AggregateType;
import com.ecommerce.product.domain.model.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "outbox_events")
public class OutboxEventEntity {

  @Id
  private UUID id;

  private String aggregateId;

  @Enumerated(EnumType.STRING)
  private AggregateType aggregateType;

  @Enumerated(EnumType.STRING)
  private EventType eventType;

  private String payload;
  private Instant createdAt;
  private boolean published;

}
