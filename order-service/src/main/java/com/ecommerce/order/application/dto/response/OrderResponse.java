package com.ecommerce.order.application.dto.response;

import com.ecommerce.order.domain.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderCreatedResponse(
    String id,
    String userId,
    List<OrderItemResponse> items,
    BigDecimal totalAmount,
    OrderStatus status,
    Instant createdAt,
    Instant updatedAt
) {}
