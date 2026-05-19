package com.tkart.ecommerce.controllers;

import com.tkart.ecommerce.models.dto.common.ApiResponse;
import com.tkart.ecommerce.models.dto.product.ProductCreateRequest;
import com.tkart.ecommerce.models.entities.Product;
import com.tkart.ecommerce.models.entities.Seller;
import com.tkart.ecommerce.repositories.SellerRepository;
import com.tkart.ecommerce.services.interfaces.SellerProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seller/products")
@RequiredArgsConstructor
@Tag(name = "Seller Product", description = "Seller quản lý sản phẩm (US-15.1)")
public class SellerProductController {

    private final SellerProductService sellerProductService;
    private final SellerRepository sellerRepository;
    private final com.tkart.ecommerce.repositories.UserRepository userRepository;

    private String getSellerId(Authentication authentication) {
        String email = authentication.getName();
        com.tkart.ecommerce.models.entities.User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Seller seller = sellerRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Seller profile not found"));
                
        if (seller.getStatus() != com.tkart.ecommerce.models.enums.SellerStatus.ACTIVE) {
            throw new com.tkart.ecommerce.exceptions.BadRequestException("Seller profile is not ACTIVE. Cannot create products. (BR19-1)");
        }
        
        return seller.getId();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SELLER')")
    @Operation(summary = "Seller đăng sản phẩm mới", description = "Sản phẩm sẽ vào trạng thái PENDING chờ Admin duyệt")
    public ResponseEntity<ApiResponse<Product>> createProduct(
            Authentication authentication,
            @Valid @RequestBody ProductCreateRequest request) {
        
        String sellerId = getSellerId(authentication);
        Product product = sellerProductService.createProduct(sellerId, request);
        
        return ResponseEntity.ok(ApiResponse.created("Product created successfully and is pending approval", product));
    }
}
