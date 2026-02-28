package com.ecommerce.order.infrastructure.persistence.mongo.repository;

import com.ecommerce.order.infrastructure.persistence.mongo.entity.ProductSnapshot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductSnapshotRepository extends MongoRepository<ProductSnapshot, Long> {

}
