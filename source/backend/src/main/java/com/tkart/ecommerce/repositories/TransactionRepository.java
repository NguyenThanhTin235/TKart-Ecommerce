package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findBySellerId(String sellerId);
}
