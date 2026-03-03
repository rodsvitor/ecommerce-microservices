package com.ecommerce.order.infrastructure.persistence.mongo.repository;

import com.ecommerce.order.infrastructure.persistence.mongo.entity.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderMongoRepository extends MongoRepository<OrderDocument, String> {
}
