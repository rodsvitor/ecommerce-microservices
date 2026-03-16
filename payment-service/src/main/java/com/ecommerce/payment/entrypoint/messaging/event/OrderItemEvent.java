package com.ecommerce.payment.entrypoint.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEvent {

  private Long productId;
  private String productName;
  private BigDecimal productPrice;

  private Integer quantity;
  private BigDecimal subtotal;

}
