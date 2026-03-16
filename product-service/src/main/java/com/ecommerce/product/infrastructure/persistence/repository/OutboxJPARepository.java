package com.ecommerce.product.infrastructure.persistence.repository;

import com.ecommerce.product.infrastructure.persistence.entity.OutboxEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutboxJPARepository extends JpaRepository<OutboxEventEntity, UUID> {

  List<OutboxEventEntity> findAllByPublishedFalse();

}
