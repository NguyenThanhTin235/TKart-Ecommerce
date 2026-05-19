package com.tkart.ecommerce.models.embedded;

import lombok.Data;
import java.util.Map;

@Data
public class CartItem {
    private String productId;
    private String title;
    private String image;
    private Long price;
    private Long mrp;
    private Integer quantity;
    private Map<String, String> selectedAttributes;
    private String sellerId;
}
