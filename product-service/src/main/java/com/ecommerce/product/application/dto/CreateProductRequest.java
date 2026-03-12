package com.ecommerce.product.application.dto;

import java.math.BigDecimal;

public record CreateProductRequest(String name, BigDecimal price) {}
