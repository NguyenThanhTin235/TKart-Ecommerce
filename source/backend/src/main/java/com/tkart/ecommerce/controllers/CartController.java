package com.tkart.ecommerce.controllers;

import com.tkart.ecommerce.models.dto.cart.CartItemRequest;
import com.tkart.ecommerce.models.dto.cart.CartItemUpdateRequest;
import com.tkart.ecommerce.models.dto.cart.CartResponse;
import com.tkart.ecommerce.models.dto.common.ApiResponse;
import com.tkart.ecommerce.models.entities.User;
import com.tkart.ecommerce.repositories.UserRepository;
import com.tkart.ecommerce.services.interfaces.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "Quản lý giỏ hàng")
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    private String getUserId(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user.getId();
    }

    @GetMapping
    @Operation(summary = "Lấy giỏ hàng của người dùng", description = "Lấy toàn bộ sản phẩm trong giỏ hàng (US-03.2)")
    public ResponseEntity<ApiResponse<CartResponse>> getCart(Authentication authentication) {
        String userId = getUserId(authentication);
        CartResponse cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(ApiResponse.ok("Cart retrieved successfully", cart));
    }

    @PostMapping("/items")
    @Operation(summary = "Thêm sản phẩm vào giỏ", description = "Thêm mới hoặc cộng dồn số lượng nếu đã có (US-03.1)")
    public ResponseEntity<ApiResponse<CartResponse>> addItemToCart(
            Authentication authentication,
            @Valid @RequestBody CartItemRequest request) {
        String userId = getUserId(authentication);
        CartResponse cart = cartService.addItemToCart(userId, request);
        return ResponseEntity.ok(ApiResponse.ok("Item added to cart", cart));
    }

    @PutMapping("/items/{productId}")
    @Operation(summary = "Cập nhật số lượng sản phẩm", description = "Thay đổi số lượng của sản phẩm trong giỏ (US-03.3)")
    public ResponseEntity<ApiResponse<CartResponse>> updateCartItem(
            Authentication authentication,
            @PathVariable String productId,
            @Valid @RequestBody CartItemUpdateRequest request) {
        String userId = getUserId(authentication);
        CartResponse cart = cartService.updateCartItem(userId, productId, request);
        return ResponseEntity.ok(ApiResponse.ok("Cart updated successfully", cart));
    }

    @DeleteMapping("/items/{productId}")
    @Operation(summary = "Xóa sản phẩm khỏi giỏ", description = "Xóa hoàn toàn sản phẩm khỏi giỏ hàng (US-03.4)")
    public ResponseEntity<ApiResponse<CartResponse>> removeCartItem(
            Authentication authentication,
            @PathVariable String productId) {
        String userId = getUserId(authentication);
        CartResponse cart = cartService.removeCartItem(userId, productId);
        return ResponseEntity.ok(ApiResponse.ok("Item removed from cart", cart));
    }

    @DeleteMapping
    @Operation(summary = "Làm sạch giỏ hàng", description = "Xóa tất cả sản phẩm trong giỏ")
    public ResponseEntity<ApiResponse<Void>> clearCart(Authentication authentication) {
        String userId = getUserId(authentication);
        cartService.clearCart(userId);
        return ResponseEntity.ok(ApiResponse.ok("Cart cleared successfully", null));
    }
}
