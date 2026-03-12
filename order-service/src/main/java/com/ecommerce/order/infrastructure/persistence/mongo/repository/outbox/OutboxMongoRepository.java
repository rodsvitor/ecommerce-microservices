package com.ecommerce.order.infrastructure.persistence.mongo.repository.outbox;

import com.ecommerce.order.infrastructure.persistence.mongo.entity.OutboxEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface OutboxMongoRepository extends MongoRepository<OutboxEventDocument, UUID> {

  List<OutboxEventDocument> findByPublishedFalse();

}
