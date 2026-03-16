package com.ecommerce.product.application.dto;

import java.math.BigDecimal;

public record UpdatedProductResponse(Long id, String name, BigDecimal price) {
}
