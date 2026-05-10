package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.PaymentOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PaymentOrderRepository extends MongoRepository<PaymentOrder, String> {
    List<PaymentOrder> findByUserId(String userId);
}
