package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
     User findByEmail(String email);
}
