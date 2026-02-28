package com.ecommerce.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  private Long id;
  private String name;
  private BigDecimal price;
  private Instant createdAt;
  private Instant updatedAt;

}
