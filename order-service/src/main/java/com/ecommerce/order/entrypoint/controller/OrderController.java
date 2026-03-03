package com.ecommerce.order.entrypoint.controller;


import com.ecommerce.order.application.dto.request.CreateOrderRequest;
import com.ecommerce.order.application.dto.response.OrderCreatedResponse;
import com.ecommerce.order.application.usecase.CreateOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final CreateOrderUseCase createOrderUseCase;

  @PostMapping
  public ResponseEntity<OrderCreatedResponse> create(@RequestBody CreateOrderRequest request) {
    return new ResponseEntity<>(createOrderUseCase.execute(request), HttpStatus.CREATED);
  }

}
