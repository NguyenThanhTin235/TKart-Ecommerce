package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findBySellerId(String sellerId);
}
