package com.tkart.ecommerce.models.dto.product;

import com.tkart.ecommerce.models.embedded.ProductAttribute;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductSummaryResponse {
    private String id;
    private String name;
    private String slug;
    private String image;
    private Long sellingPrice;
    private Long mrp;
    private Integer discountPercent;
    private Double rating;
    private Integer reviewsCount;
    private String categoryId;
    private List<ProductAttribute> attributes;
}
