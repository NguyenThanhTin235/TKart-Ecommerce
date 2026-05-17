package com.tkart.ecommerce.models.dto.product;

import lombok.Data;

@Data
public class ProductFilterRequest {
    private String keyword;
    private String category;     // ID danh mục bất kỳ cấp nào
    private Long minPrice;
    private Long maxPrice;
    private Integer minDiscount;
    private String color;
    private String size;
    private Double minRating;
    private String sortBy = "createdAt";   // price | createdAt | sales
    private String sortDir = "desc";       // asc | desc
    private int page = 0;
    private int limit = 12;
}
