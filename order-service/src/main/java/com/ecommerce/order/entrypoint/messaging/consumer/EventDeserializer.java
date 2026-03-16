package com.ecommerce.order.entrypoint.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventDeserializer {

  private final ObjectMapper objectMapper;

  public <T> T getObject(String payload, Class<T> valueType) {

    try {
      return objectMapper.readValue(payload, valueType);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize event", e);
    }

  }

}
