package com.ecommerce.payment.application.exception;

import java.util.UUID;

public class DuplicateEventException extends RuntimeException {

  public DuplicateEventException(UUID eventId) {
    super("Event already processed: " + eventId);
  }

}
