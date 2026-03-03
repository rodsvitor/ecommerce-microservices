package com.ecommerce.product.application.mapper;

import com.ecommerce.product.application.usecase.CreateProductUseCase;
import com.ecommerce.product.application.usecase.ProductQueryService;
import com.ecommerce.product.application.usecase.UpdateProductUseCase.UpdatedProductResponse;
import com.ecommerce.product.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapperDTO {

  Product toDomain(CreateProductUseCase.CreateProductRequest product);

  CreateProductUseCase.ProductCreatedResponse toCreationResponse(Product product);

  UpdatedProductResponse toUpdatedProductResponse(Product product);

  ProductQueryService.QueriedProductResponse toQueriedResponse(Product product);

}
