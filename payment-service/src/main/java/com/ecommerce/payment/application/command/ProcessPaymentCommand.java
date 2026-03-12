package com.ecommerce.payment.application.command;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;


@Builder
@Data
public class ProcessPaymentCommand {

  private final UUID orderId;
  private final UUID userId;
  private final BigDecimal amount;

}
