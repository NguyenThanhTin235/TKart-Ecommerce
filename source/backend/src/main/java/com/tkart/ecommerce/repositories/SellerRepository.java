package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface SellerRepository extends MongoRepository<Seller, String> {
    Optional<Seller> findByUserId(String userId);
}
