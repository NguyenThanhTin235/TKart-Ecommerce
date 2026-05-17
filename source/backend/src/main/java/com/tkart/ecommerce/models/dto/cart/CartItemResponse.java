package com.tkart.ecommerce.models.dto.cart;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class CartItemResponse {
    private String productId;
    private String title;
    private String image;
    private Long price;
    private Integer quantity;
    private Map<String, String> selectedAttributes;
    private String sellerId;
}
