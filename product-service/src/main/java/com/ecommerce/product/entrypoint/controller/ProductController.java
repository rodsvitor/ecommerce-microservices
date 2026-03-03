package com.ecommerce.product.entrypoint.controller;

import com.ecommerce.product.application.usecase.CreateProductUseCase;
import com.ecommerce.product.application.usecase.CreateProductUseCase.CreateProductRequest;
import com.ecommerce.product.application.usecase.CreateProductUseCase.ProductCreatedResponse;
import com.ecommerce.product.application.usecase.DeleteProductUseCase;
import com.ecommerce.product.application.usecase.ProductQueryService;
import com.ecommerce.product.application.usecase.ProductQueryService.QueriedProductResponse;
import com.ecommerce.product.application.usecase.UpdateProductUseCase;
import com.ecommerce.product.application.usecase.UpdateProductUseCase.UpdateProductRequest;
import com.ecommerce.product.application.usecase.UpdateProductUseCase.UpdatedProductResponse;
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
  private final UpdateProductUseCase updateProductUseCase;
  private final DeleteProductUseCase deleteProductUseCase;
  private final ProductQueryService productQueryService;

  @PostMapping
  public ResponseEntity<ProductCreatedResponse> create(@RequestBody CreateProductRequest productRequest) {
    return new ResponseEntity<>(createProductUseCase.execute(productRequest), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<QueriedProductResponse>> listAll() {
    return ResponseEntity.ok(productQueryService.listAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<QueriedProductResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(productQueryService.findById(id));
  }

  @PutMapping
  public ResponseEntity<UpdatedProductResponse> update(@RequestBody UpdateProductRequest updateProductRequest) {
    return ResponseEntity.ok(updateProductUseCase.execute(updateProductRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteProductUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }

}
