package com.tkart.ecommerce.services.impl;

import com.tkart.ecommerce.models.entities.User;
import com.tkart.ecommerce.repositories.UserRepository;
import com.tkart.ecommerce.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
     @Autowired
     private UserRepository userRepository;

     @Override
     public User findByEmail(String email) {
          return userRepository.findByEmail(email);
     }
}
