package com.tkart.ecommerce.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "chat_rooms")
@CompoundIndex(name = "customer_seller_idx", def = "{'customerId': 1, 'sellerId': 1}", unique = true)
@Data
public class ChatRoom extends BaseDocument {

    private String customerId; // User reference
    private String sellerId; // Seller reference

    private String lastMessage;
}
