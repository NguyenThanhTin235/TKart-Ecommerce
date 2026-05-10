package com.tkart.ecommerce.models.embedded;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductVariant {
    private String sku;
    private Map<String, String> attributes; // e.g., {"color": "red", "size": "M"}
    private Long mrpPrice;
    private Long sellingPrice;
    private Integer discountPercent;
    private Integer quantity;
    private List<String> images;
}
