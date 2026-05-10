package com.tkart.ecommerce.services.interfaces;

import com.tkart.ecommerce.models.entities.User;

public interface UserService {
     User findByEmail(String email);
}
