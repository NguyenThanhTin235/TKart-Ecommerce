package com.tkart.ecommerce.controllers;

import com.tkart.ecommerce.models.dto.admin.ApprovalStatusRequest;
import com.tkart.ecommerce.models.dto.common.ApiResponse;
import com.tkart.ecommerce.models.entities.Product;
import com.tkart.ecommerce.models.entities.Seller;
import com.tkart.ecommerce.services.interfaces.AdminApprovalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/approvals")
@RequiredArgsConstructor
@Tag(name = "Admin Approval", description = "Quản trị phê duyệt gian hàng và sản phẩm (US-19.1, US-20.1)")
public class AdminApprovalController {

    private final AdminApprovalService approvalService;

    @GetMapping("/sellers")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Lấy danh sách Seller chờ duyệt", description = "Lấy danh sách PENDING_VERIFICATION")
    public ResponseEntity<ApiResponse<List<Seller>>> getPendingSellers() {
        List<Seller> sellers = approvalService.getPendingSellers();
        return ResponseEntity.ok(ApiResponse.ok("Pending sellers retrieved", sellers));
    }

    @PutMapping("/sellers/{sellerId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Phê duyệt/Từ chối Seller", description = "Duyệt Seller sang ACTIVE")
    public ResponseEntity<ApiResponse<Seller>> approveSeller(
            @PathVariable String sellerId,
            @Valid @RequestBody ApprovalStatusRequest request) {
        Seller seller = approvalService.approveSeller(sellerId, request);
        return ResponseEntity.ok(ApiResponse.ok("Seller status updated", seller));
    }

    @GetMapping("/products")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Lấy danh sách Product chờ duyệt", description = "Lấy danh sách PENDING")
    public ResponseEntity<ApiResponse<List<Product>>> getPendingProducts() {
        List<Product> products = approvalService.getPendingProducts();
        return ResponseEntity.ok(ApiResponse.ok("Pending products retrieved", products));
    }

    @PutMapping("/products/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Phê duyệt/Từ chối Product", description = "Duyệt Product sang PUBLISHED")
    public ResponseEntity<ApiResponse<Product>> approveProduct(
            @PathVariable String productId,
            @Valid @RequestBody ApprovalStatusRequest request) {
        Product product = approvalService.approveProduct(productId, request);
        return ResponseEntity.ok(ApiResponse.ok("Product status updated", product));
    }
}
