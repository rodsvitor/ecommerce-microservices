package com.ecommerce.order.infrastructure.persistence.mongo.mapper;

import com.ecommerce.order.domain.model.Product;
import com.ecommerce.order.infrastructure.persistence.mongo.entity.ProductSnapshot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapperORM {

  Product toDomain(ProductSnapshot productSnapshot);

}
