package com.ecommerce.product.infrastructure.messaging;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.kafka.topics")
public record KafkaTopicsProperties(
    String productCreated,
    String productUpdated,
    String productDeleted) {}
