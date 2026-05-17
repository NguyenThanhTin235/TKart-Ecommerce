package com.tkart.ecommerce.services.interfaces;

import com.tkart.ecommerce.models.dto.product.CategoryTreeResponse;
import com.tkart.ecommerce.models.dto.product.ProductDetailResponse;
import com.tkart.ecommerce.models.dto.product.ProductFilterRequest;
import com.tkart.ecommerce.models.dto.product.ProductSummaryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductPublicService {
    Page<ProductSummaryResponse> searchProducts(ProductFilterRequest filter);
    ProductDetailResponse getProductById(String id);
    List<CategoryTreeResponse> getCategoryTree();
}
