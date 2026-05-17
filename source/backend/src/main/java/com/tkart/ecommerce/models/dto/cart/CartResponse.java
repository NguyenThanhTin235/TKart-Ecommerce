package com.tkart.ecommerce.models.dto.cart;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponse {
    private String id;
    private String userId;
    private List<CartItemResponse> cartItems;
    private Long totalSellingPrice;
    private Integer totalItems;
}
