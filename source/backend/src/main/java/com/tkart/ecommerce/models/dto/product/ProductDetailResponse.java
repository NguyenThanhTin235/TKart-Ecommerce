package com.tkart.ecommerce.models.dto.product;

import com.tkart.ecommerce.models.embedded.ProductAttribute;
import com.tkart.ecommerce.models.embedded.ProductVariant;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProductDetailResponse {
    private String id;
    private String name;
    private String title;
    private String slug;
    private String description;
    private List<String> images;
    private Long sellingPrice;
    private Long mrp;
    private Integer discountPercent;
    private Integer stock;
    private Double rating;
    private Integer reviewsCount;
    private String categoryId;
    private String categoryL3Id;
    private List<ProductAttribute> attributes;
    private List<ProductVariant> variants;
    private String sellerId;
    private LocalDateTime createdAt;
    private List<ProductSummaryResponse> relatedProducts;
}
