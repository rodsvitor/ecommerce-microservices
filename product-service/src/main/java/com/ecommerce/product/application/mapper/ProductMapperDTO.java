package com.ecommerce.product.application.mapper;

import com.ecommerce.product.application.dto.CreateProductRequest;
import com.ecommerce.product.application.dto.ProductCreatedResponse;
import com.ecommerce.product.application.dto.QueriedProductResponse;
import com.ecommerce.product.application.dto.UpdatedProductResponse;
import com.ecommerce.product.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapperDTO {

  Product toDomain(CreateProductRequest product);

  ProductCreatedResponse toCreationResponse(Product product);

  UpdatedProductResponse toUpdatedProductResponse(Product product);

  QueriedProductResponse toQueriedResponse(Product product);

}
