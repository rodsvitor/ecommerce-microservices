package com.ecommerce.product.application.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record QueriedProductResponse(
    Long id,
    String name,
    BigDecimal price,
    Instant createdAt,
    Instant updatedAt) {
}
