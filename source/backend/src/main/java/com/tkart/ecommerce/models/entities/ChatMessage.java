package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "chat_messages")
@CompoundIndex(name = "chatroom_created_idx", def = "{'chatRoomId': 1, 'createdAt': -1}")
@Data
public class ChatMessage extends BaseDocument {

    @Indexed
    private String chatRoomId;

    private String senderId;
    private Role senderRole;

    private String content;
    private boolean isRead = false;
}
