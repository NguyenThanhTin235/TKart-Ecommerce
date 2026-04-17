package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
     User findByUsername(String username);
}
