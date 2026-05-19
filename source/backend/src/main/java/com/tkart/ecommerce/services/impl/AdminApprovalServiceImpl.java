package com.tkart.ecommerce.services.impl;

import com.tkart.ecommerce.exceptions.ResourceNotFoundException;
import com.tkart.ecommerce.models.dto.admin.ApprovalStatusRequest;
import com.tkart.ecommerce.models.entities.Product;
import com.tkart.ecommerce.models.entities.Seller;
import com.tkart.ecommerce.models.enums.ProductStatus;
import com.tkart.ecommerce.models.enums.SellerStatus;
import com.tkart.ecommerce.repositories.ProductRepository;
import com.tkart.ecommerce.repositories.SellerRepository;
import com.tkart.ecommerce.services.interfaces.AdminApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminApprovalServiceImpl implements AdminApprovalService {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final com.tkart.ecommerce.repositories.AuditLogRepository auditLogRepository;

    @Override
    public List<Seller> getPendingSellers() {
        return sellerRepository.findByStatus(SellerStatus.PENDING_VERIFICATION);
    }

    @Override
    public Seller approveSeller(String sellerId, ApprovalStatusRequest request) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found"));
        
        if (request.getApprove()) {
            seller.setStatus(SellerStatus.ACTIVE);
        } else {
            seller.setStatus(SellerStatus.REJECTED);
        }
        
        // Log hành động theo NFR19-1
        com.tkart.ecommerce.models.entities.AuditLog log = new com.tkart.ecommerce.models.entities.AuditLog();
        log.setActorRole(com.tkart.ecommerce.models.enums.Role.ROLE_ADMIN);
        log.setAction(request.getApprove() ? "APPROVE_SELLER" : "REJECT_SELLER");
        log.setTargetType("SELLER");
        log.setTargetId(sellerId);
        log.setDetails(request.getReason() != null ? request.getReason() : "No reason provided");
        auditLogRepository.save(log);
        
        return sellerRepository.save(seller);
    }

    @Override
    public List<Product> getPendingProducts() {
        return productRepository.findByStatus(ProductStatus.PENDING);
    }

    @Override
    public Product approveProduct(String productId, ApprovalStatusRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
                
        if (request.getApprove()) {
            product.setStatus(ProductStatus.PUBLISHED);
        } else {
            product.setStatus(ProductStatus.REJECTED);
        }
        
        com.tkart.ecommerce.models.entities.AuditLog log = new com.tkart.ecommerce.models.entities.AuditLog();
        log.setActorRole(com.tkart.ecommerce.models.enums.Role.ROLE_ADMIN);
        log.setAction(request.getApprove() ? "APPROVE_PRODUCT" : "REJECT_PRODUCT");
        log.setTargetType("PRODUCT");
        log.setTargetId(productId);
        log.setDetails(request.getReason() != null ? request.getReason() : "No reason provided");
        auditLogRepository.save(log);
        
        return productRepository.save(product);
    }
}
