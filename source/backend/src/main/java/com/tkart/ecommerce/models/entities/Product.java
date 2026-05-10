package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.embedded.ProductVariant;
import com.tkart.ecommerce.models.enums.ProductStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "products")
@Data
public class Product extends BaseDocument {

    @Indexed
    private String sellerId; // Reference to Seller

    private String title;
    private String slug;
    private String description;

    @Indexed
    private String categoryL3Id; // Reference to Category Level 3

    private List<String> images;
    
    private List<ProductVariant> variants;

    @Indexed
    private Long minSellingPrice; // Computed
    private Integer maxDiscountPercent; // Computed
    private Integer totalQuantity; // Computed

    @Indexed
    private ProductStatus status = ProductStatus.PENDING;
}
