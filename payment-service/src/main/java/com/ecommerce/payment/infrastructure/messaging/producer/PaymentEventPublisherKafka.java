package com.ecommerce.payment.infrastructure.messaging.producer;

import com.ecommerce.payment.domain.model.Payment;
import com.ecommerce.payment.infrastructure.messaging.mapper.PaymentMapperEvent;
import com.ecommerce.payment.infrastructure.messaging.topic.PaymentTopicsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventPublisherKafka {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final PaymentTopicsProperties paymentTopics;

  public void publishPaymentProcessed(Payment payment) {

    var paymentEvent = PaymentMapperEvent.toPaymentProcessedEvent(payment);

    kafkaTemplate.send(paymentTopics.paymentProcessed(), paymentEvent);

    log.info("Published payment processed event: {}", payment);

  }

}
