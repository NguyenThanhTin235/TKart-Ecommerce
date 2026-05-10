package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.ReturnRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ReturnRequestRepository extends MongoRepository<ReturnRequest, String> {
    Optional<ReturnRequest> findByOrderId(String orderId);
}
