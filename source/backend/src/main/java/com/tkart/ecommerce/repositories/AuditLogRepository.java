package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditLogRepository extends MongoRepository<AuditLog, String> {
}
