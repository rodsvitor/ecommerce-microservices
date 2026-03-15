package com.ecommerce.payment.domain.processedevent;

import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.UUID;


public record ProcessedEvent(
    @EqualsAndHashCode.Include
    UUID eventId,
    Instant processedAt) {

    public static ProcessedEvent of(UUID eventId) {
        return new ProcessedEvent(eventId, Instant.now());
    }

}
