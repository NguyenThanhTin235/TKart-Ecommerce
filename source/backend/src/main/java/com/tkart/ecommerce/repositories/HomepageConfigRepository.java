package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.HomepageConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HomepageConfigRepository extends MongoRepository<HomepageConfig, String> {
}
