package com.tkart.ecommerce.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "wishlists")
@Data
public class Wishlist extends BaseDocument {

    @Indexed(unique = true)
    private String userId; // 1-1 with User

    private List<String> productIds;
}
