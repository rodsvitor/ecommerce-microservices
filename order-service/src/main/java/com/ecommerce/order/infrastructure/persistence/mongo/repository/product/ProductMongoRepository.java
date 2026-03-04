package com.ecommerce.order.infrastructure.persistence.mongo.repository.product;

import com.ecommerce.order.infrastructure.persistence.mongo.entity.ProductSnapshot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<ProductSnapshot, Long> {

}
