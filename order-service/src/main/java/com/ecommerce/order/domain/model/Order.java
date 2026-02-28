package com.ecommerce.order.domain.model;

import java.math.BigDecimal;

public class Order {

  private String id;
  private Long productId;
  private String productName;
  private BigDecimal productPrice;
  private String currency;
  private String status;

}