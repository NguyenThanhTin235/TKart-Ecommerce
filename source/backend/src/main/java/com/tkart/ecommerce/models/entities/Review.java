package com.tkart.ecommerce.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "reviews")
@CompoundIndex(name = "user_product_idx", def = "{'userId': 1, 'productId': 1}", unique = true)
@Data
public class Review extends BaseDocument {

    @Indexed
    private String userId;

    @Indexed
    private String productId;

    private String orderId;

    private Integer rating; // 1-5
    private String comment;
    private List<String> media;
}
