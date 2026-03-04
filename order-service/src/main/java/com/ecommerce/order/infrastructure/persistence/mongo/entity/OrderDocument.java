package com.ecommerce.order.infrastructure.persistence.mongo.entity;

import com.ecommerce.order.domain.model.OrderItem;
import com.ecommerce.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class OrderDocument {

  @Id
  private UUID id;
  private UUID userId;
  private List<OrderItem> items;
  private BigDecimal totalAmount;
  private OrderStatus status;
  private Instant createdAt;
  private Instant updatedAt;

}