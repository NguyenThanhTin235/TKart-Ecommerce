package com.tkart.ecommerce.services.interfaces;

import com.tkart.ecommerce.models.entities.User;

public interface UserService {
     User findByUsername(String username);
}
