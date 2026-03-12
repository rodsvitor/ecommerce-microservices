package com.ecommerce.payment.domain.repository;

import com.ecommerce.payment.domain.model.Payment;

public interface PaymentRepository {

  Payment save(Payment payment);

}
