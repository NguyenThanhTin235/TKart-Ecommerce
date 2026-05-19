package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import java.util.List;

public interface SellerRepository extends MongoRepository<Seller, String> {
    Optional<Seller> findByUserId(String userId);
    List<Seller> findByStatus(com.tkart.ecommerce.models.enums.SellerStatus status);
}
