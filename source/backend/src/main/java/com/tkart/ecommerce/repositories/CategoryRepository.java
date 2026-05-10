package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
