package com.ecommerce.order.infrastructure.persistence.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product_snapshots")
public class ProductSnapshot {

  @Id
  private Long id;

  private String name;
  private BigDecimal price;
  private Instant createdAt;
  private Instant updatedAt;

}
