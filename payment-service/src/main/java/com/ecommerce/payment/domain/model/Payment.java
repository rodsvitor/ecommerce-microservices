package com.ecommerce.payment.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Payment {

  private UUID id;
  private UUID orderId;
  private PaymentStatus status;

}
