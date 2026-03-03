package com.ecommerce.product.infrastructure.persistence.mapper;


import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.infrastructure.persistence.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapperORM {

  Product toDomain(ProductEntity product);

  ProductEntity toEntity(Product product);

}
