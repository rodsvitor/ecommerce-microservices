package com.ecommerce.payment.infrastructure.persistence.entity.repository;

import com.ecommerce.payment.domain.model.Payment;
import com.ecommerce.payment.domain.repository.PaymentRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

  @Override
  public Payment save(Payment payment) {
    // TODO implement ORM
    return payment;
  }
}
