package com.ecommerce.order.application.usecase;

import com.ecommerce.order.domain.model.Order;
import com.ecommerce.order.domain.model.OrderStatus;
import com.ecommerce.order.domain.model.Payment;
import com.ecommerce.order.domain.model.PaymentStatus;
import com.ecommerce.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UpdateStatusOrderBasedOnProcessedPaymentUseCase {

  private final OrderRepository orderRepository;

  public void execute(Payment payment) {

    Order order = orderRepository.findById(payment.getOrderId())
        .orElseThrow(() -> new RuntimeException("Order not found"));

    if (PaymentStatus.SUCCESS == payment.getStatus()) {
      order.setStatus(OrderStatus.PAID);
    } else {
      order.setStatus(OrderStatus.PAYMENT_FAILED);
    }

    order.setUpdatedAt(Instant.now());

    orderRepository.save(order);

  }

}
