package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.embedded.CartItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "carts")
@Data
public class Cart extends BaseDocument {

    @Indexed(unique = true)
    private String userId; // FK 1-1 to User

    private List<CartItem> cartItems;

    private Long totalSellingPrice;
    private Integer totalItems;
}
