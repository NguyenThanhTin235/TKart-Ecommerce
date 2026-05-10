package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    Page<ChatMessage> findByChatRoomId(String chatRoomId, Pageable pageable);
}
