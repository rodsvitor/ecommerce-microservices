package com.ecommerce.auth.application.dto;

public record LoginRequest(
    String login,
    String password
) {}
