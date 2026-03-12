package com.ecommerce.product.application.dto;

import java.math.BigDecimal;

public record ProductCreatedResponse(Long id, String name, BigDecimal price) {}
