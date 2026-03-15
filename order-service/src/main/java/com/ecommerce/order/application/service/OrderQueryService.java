package com.ecommerce.order.application.service;

import com.ecommerce.order.application.dto.response.OrderResponse;
import com.ecommerce.order.application.mapper.OrderMapperDTO;
import com.ecommerce.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

  private final OrderRepository orderRepository;
  private final OrderMapperDTO mapperDTO;

  public List<OrderResponse> findAll() {

    return orderRepository
        .findAll()
        .stream()
        .map(mapperDTO::toOrderCreatedResponse)
        .toList();

  }

  public OrderResponse findById(UUID id) {

    return orderRepository.findById(id)
        .map(mapperDTO::toOrderCreatedResponse)
        .orElseThrow(() -> new RuntimeException("Order not found"));

  }

}
