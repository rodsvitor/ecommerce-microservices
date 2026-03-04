package com.ecommerce.order.entrypoint.controller;


import com.ecommerce.order.application.dto.request.CreateOrderRequest;
import com.ecommerce.order.application.dto.response.OrderResponse;
import com.ecommerce.order.application.usecase.CreateOrderUseCase;
import com.ecommerce.order.application.usecase.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final CreateOrderUseCase createOrderUseCase;
  private final OrderQueryService orderQueryService;

  @PostMapping
  public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest request) {
    return new ResponseEntity<>(createOrderUseCase.execute(request), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> findAll() {
    return ResponseEntity.ok(orderQueryService.findAll());
  }

}
