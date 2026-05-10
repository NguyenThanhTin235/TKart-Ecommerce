package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.Deal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DealRepository extends MongoRepository<Deal, String> {
}
