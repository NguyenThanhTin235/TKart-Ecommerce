package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    List<Category> findByParentId(String parentId);
    List<Category> findByLevel(Integer level);
}
