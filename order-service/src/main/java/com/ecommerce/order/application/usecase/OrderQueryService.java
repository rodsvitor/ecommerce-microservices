package com.ecommerce.order.application.usecase;

import com.ecommerce.order.application.dto.response.OrderResponse;
import com.ecommerce.order.application.mapper.OrderMapperDTO;
import com.ecommerce.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
