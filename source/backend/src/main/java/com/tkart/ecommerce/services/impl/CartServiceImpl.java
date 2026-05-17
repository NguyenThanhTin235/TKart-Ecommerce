package com.tkart.ecommerce.services.impl;

import com.tkart.ecommerce.exceptions.ResourceNotFoundException;
import com.tkart.ecommerce.exceptions.BadRequestException;
import com.tkart.ecommerce.models.dto.cart.CartItemRequest;
import com.tkart.ecommerce.models.dto.cart.CartItemResponse;
import com.tkart.ecommerce.models.dto.cart.CartItemUpdateRequest;
import com.tkart.ecommerce.models.dto.cart.CartResponse;
import com.tkart.ecommerce.models.embedded.CartItem;
import com.tkart.ecommerce.models.entities.Cart;
import com.tkart.ecommerce.models.entities.Product;
import com.tkart.ecommerce.repositories.CartRepository;
import com.tkart.ecommerce.repositories.ProductRepository;
import com.tkart.ecommerce.services.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public CartResponse getCartByUserId(String userId) {
        Cart cart = getOrCreateCart(userId);
        return mapToResponse(cart);
    }

    @Override
    public CartResponse addItemToCart(String userId, CartItemRequest request) {
        Cart cart = getOrCreateCart(userId);
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (product.getStock() < request.getQuantity()) {
            throw new BadRequestException("Not enough stock for this product");
        }

        Optional<CartItem> existingItemOpt = cart.getCartItems().stream()
                .filter(item -> item.getProductId().equals(request.getProductId()))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            int newQuantity = existingItem.getQuantity() + request.getQuantity();
            if (product.getStock() < newQuantity) {
                throw new BadRequestException("Not enough stock for this product");
            }
            existingItem.setQuantity(newQuantity);
            // Cập nhật lại giá
            existingItem.setPrice(product.getSellingPrice());
        } else {
            CartItem newItem = new CartItem();
            newItem.setProductId(product.getId());
            newItem.setTitle(product.getName());
            newItem.setImage(product.getImages() != null && !product.getImages().isEmpty() ? product.getImages().get(0) : "");
            newItem.setPrice(product.getSellingPrice());
            newItem.setQuantity(request.getQuantity());
            newItem.setSelectedAttributes(request.getSelectedAttributes());
            newItem.setSellerId(product.getSellerId());

            cart.getCartItems().add(newItem);
        }

        updateCartTotals(cart);
        cartRepository.save(cart);

        return mapToResponse(cart);
    }

    @Override
    public CartResponse updateCartItem(String userId, String productId, CartItemUpdateRequest request) {
        Cart cart = getOrCreateCart(userId);
        
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in cart"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (product.getStock() < request.getQuantity()) {
            throw new BadRequestException("Not enough stock for this product");
        }

        cartItem.setQuantity(request.getQuantity());
        updateCartTotals(cart);
        cartRepository.save(cart);

        return mapToResponse(cart);
    }

    @Override
    public CartResponse removeCartItem(String userId, String productId) {
        Cart cart = getOrCreateCart(userId);
        boolean removed = cart.getCartItems().removeIf(item -> item.getProductId().equals(productId));
        
        if (!removed) {
            throw new ResourceNotFoundException("Product not found in cart");
        }

        updateCartTotals(cart);
        cartRepository.save(cart);

        return mapToResponse(cart);
    }

    @Override
    public void clearCart(String userId) {
        Cart cart = getOrCreateCart(userId);
        cart.getCartItems().clear();
        updateCartTotals(cart);
        cartRepository.save(cart);
    }

    private Cart getOrCreateCart(String userId) {
        return cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            newCart.setCartItems(new ArrayList<>());
            newCart.setTotalItems(0);
            newCart.setTotalSellingPrice(0L);
            return cartRepository.save(newCart);
        });
    }

    private void updateCartTotals(Cart cart) {
        int totalItems = 0;
        long totalPrice = 0L;

        for (CartItem item : cart.getCartItems()) {
            totalItems += item.getQuantity();
            totalPrice += item.getPrice() * item.getQuantity();
        }

        cart.setTotalItems(totalItems);
        cart.setTotalSellingPrice(totalPrice);
    }

    private CartResponse mapToResponse(Cart cart) {
        List<CartItemResponse> items = cart.getCartItems().stream()
                .map(item -> CartItemResponse.builder()
                        .productId(item.getProductId())
                        .title(item.getTitle())
                        .image(item.getImage())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .selectedAttributes(item.getSelectedAttributes())
                        .sellerId(item.getSellerId())
                        .build())
                .collect(Collectors.toList());

        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .cartItems(items)
                .totalSellingPrice(cart.getTotalSellingPrice())
                .totalItems(cart.getTotalItems())
                .build();
    }
}
