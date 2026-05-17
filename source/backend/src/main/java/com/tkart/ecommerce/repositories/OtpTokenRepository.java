package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.OtpToken;
import com.tkart.ecommerce.models.enums.OtpType;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface OtpTokenRepository extends MongoRepository<OtpToken, String> {
    Optional<OtpToken> findByCode(String code);
    Optional<OtpToken> findByEmailAndCodeAndTypeAndUsedFalse(String email, String code, OtpType type);
}
