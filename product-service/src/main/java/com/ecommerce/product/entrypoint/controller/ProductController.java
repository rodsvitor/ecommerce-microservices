package com.ecommerce.product.entrypoint.controller;

import com.ecommerce.product.application.usecase.CreateProductUseCase;
import com.ecommerce.product.application.usecase.CreateProductUseCase.CreateProductRequest;
import com.ecommerce.product.application.usecase.CreateProductUseCase.CreateProductResponse;
import com.ecommerce.product.application.usecase.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final CreateProductUseCase createProductUseCase;
  private final ProductQueryService productQueryService;

  @PostMapping
  public ResponseEntity<CreateProductResponse> create(@RequestBody CreateProductRequest productRequest) {
    return new ResponseEntity<>(createProductUseCase.execute(productRequest), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ProductQueryService.QueriedProductResponse>> listAll() {
    return ResponseEntity.ok(productQueryService.listAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductQueryService.QueriedProductResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(productQueryService.findById(id));
  }

}
