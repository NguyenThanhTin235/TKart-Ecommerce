package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.ShippingProvider;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShippingProviderRepository extends MongoRepository<ShippingProvider, String> {
}
