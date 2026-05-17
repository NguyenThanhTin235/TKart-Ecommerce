package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.dto.product.ProductFilterRequest;
import com.tkart.ecommerce.models.entities.Product;
import org.springframework.data.domain.Page;

public interface ProductCustomRepository {
    Page<Product> findByFilter(ProductFilterRequest filter, java.util.List<String> categoryL3Ids);
}
