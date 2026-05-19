package com.tkart.ecommerce.services.interfaces;

import com.tkart.ecommerce.models.dto.product.ProductCreateRequest;
import com.tkart.ecommerce.models.entities.Product;

public interface SellerProductService {
    Product createProduct(String sellerId, ProductCreateRequest request);
}
