package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.CoinWallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CoinWalletRepository extends MongoRepository<CoinWallet, String> {
    Optional<CoinWallet> findByUserId(String userId);
}
