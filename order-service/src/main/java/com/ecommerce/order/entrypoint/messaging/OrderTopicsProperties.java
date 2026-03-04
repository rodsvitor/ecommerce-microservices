package com.ecommerce.order.entrypoint.messaging;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.kafka.topics.order")
public record OrderTopicsProperties(String created) {}
