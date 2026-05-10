package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CouponRepository extends MongoRepository<Coupon, String> {
    Optional<Coupon> findByCode(String code);
}
