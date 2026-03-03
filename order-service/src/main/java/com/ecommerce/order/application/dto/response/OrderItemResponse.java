package com.ecommerce.order.application.dto.response;

public record OrderItemResponse(
    Long productId,
    Integer quantity
) {}
