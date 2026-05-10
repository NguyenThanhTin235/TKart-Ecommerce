package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.PlatformConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlatformConfigRepository extends MongoRepository<PlatformConfig, String> {
}
