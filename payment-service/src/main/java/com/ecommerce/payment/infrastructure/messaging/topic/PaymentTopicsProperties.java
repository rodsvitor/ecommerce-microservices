package com.ecommerce.payment.infrastructure.messaging.topic;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.kafka.topics.payment")
public record PaymentTopicsProperties(String paymentProcessed) {}
