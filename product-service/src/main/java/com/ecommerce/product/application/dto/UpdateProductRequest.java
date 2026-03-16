package com.ecommerce.product.application.dto;

import java.math.BigDecimal;

public record UpdateProductRequest(Long id, String name, BigDecimal price) {
}
