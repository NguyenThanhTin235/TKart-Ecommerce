package com.tkart.ecommerce.services.interfaces;

import com.tkart.ecommerce.models.dto.admin.ApprovalStatusRequest;
import com.tkart.ecommerce.models.entities.Product;
import com.tkart.ecommerce.models.entities.Seller;

import java.util.List;

public interface AdminApprovalService {
    List<Seller> getPendingSellers();
    Seller approveSeller(String sellerId, ApprovalStatusRequest request);
    
    List<Product> getPendingProducts();
    Product approveProduct(String productId, ApprovalStatusRequest request);
}
