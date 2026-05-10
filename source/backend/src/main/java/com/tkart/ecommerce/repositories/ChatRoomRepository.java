package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findByCustomerIdAndSellerId(String customerId, String sellerId);
}
