package com.tkart.ecommerce.services.interfaces;

import com.tkart.ecommerce.models.dto.cart.CartItemRequest;
import com.tkart.ecommerce.models.dto.cart.CartItemUpdateRequest;
import com.tkart.ecommerce.models.dto.cart.CartResponse;

public interface CartService {
    CartResponse getCartByUserId(String userId);
    CartResponse addItemToCart(String userId, CartItemRequest request);
    CartResponse updateCartItem(String userId, String productId, CartItemUpdateRequest request);
    CartResponse removeCartItem(String userId, String productId);
    void clearCart(String userId);
}
