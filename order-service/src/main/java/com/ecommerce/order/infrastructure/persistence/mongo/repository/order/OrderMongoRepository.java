package com.ecommerce.order.infrastructure.persistence.mongo.repository;

import com.ecommerce.order.infrastructure.persistence.mongo.entity.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface OrderMongoRepository extends MongoRepository<OrderDocument, UUID> {

}
