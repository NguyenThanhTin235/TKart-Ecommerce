package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AddressRepository extends MongoRepository<Address, String> {
    List<Address> findByUserId(String userId);
}
