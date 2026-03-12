package com.ecommerce.payment.application.usecase;

import com.ecommerce.payment.application.command.ProcessPaymentCommand;
import com.ecommerce.payment.domain.model.PaymentStatus;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProcessPaymentUseCase {

  @Retry(name = "paymentRetry")
  @CircuitBreaker(name = "paymentCircuitBreaker", fallbackMethod = "fallbackPayment")
//  @TimeLimiter(name = "paymentTimeout")
  public PaymentStatus execute(ProcessPaymentCommand command) {

    simulateLatency(command);

    if (Math.random() < .5) {
      log.error("Payment provider failure");
      throw new RuntimeException("Payment provider error");
    }

    log.info("Payment successful for order {}", command.getOrderId());

    return PaymentStatus.SUCCESS;

  }

  private void simulateLatency(ProcessPaymentCommand command) {
    try {
      log.info("🕒🕒🕒 Processing payment for order: {}", command.getOrderId());
      Thread.sleep((long) (Math.random() * 4_000));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public PaymentStatus fallbackPayment(
      ProcessPaymentCommand command,
      Throwable throwable) {
    log.error("💔 Fallback triggered for order {}", command.getOrderId());

    return PaymentStatus.FAIL;
  }

}
