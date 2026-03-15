package com.ecommerce.payment.infrastructure.persistence.repository;

import com.ecommerce.payment.domain.payment.Payment;
import com.ecommerce.payment.domain.payment.PaymentRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

  @Override
  public Payment save(Payment payment) {
    // TODO implement ORM
    return payment;
  }
}
