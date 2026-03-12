package com.ecommerce.payment.application.command;

import com.ecommerce.payment.domain.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;


@Builder
@Data
public class CreatePaymentCommand {

  private final UUID orderId;
  private final UUID userId;
  private final BigDecimal amount;
  private PaymentStatus status;

}
