package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.CouponRedemption;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CouponRedemptionRepository extends MongoRepository<CouponRedemption, String> {
}
