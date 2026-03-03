package com.ecommerce.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

  private Long productId;
  private String productName;
  private BigDecimal productPrice;

  private Integer quantity;
  private BigDecimal subtotal;

}
