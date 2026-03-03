package com.ecommerce.order.application.dto.request;

public record OrderItemRequest(
    Long productId,
    Integer quantity
) {}
