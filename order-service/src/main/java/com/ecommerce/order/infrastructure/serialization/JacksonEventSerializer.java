package com.ecommerce.order.infrastructure.serialization;


import com.ecommerce.order.application.port.EventSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JacksonEventSerializer implements EventSerializer {

  private final ObjectMapper objectMapper;

  @Override
  public String serialize(Object event) {

    try {
      return objectMapper.writeValueAsString(event);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize event", e);
    }

  }
}
