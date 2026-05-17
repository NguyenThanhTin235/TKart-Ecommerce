package com.tkart.ecommerce.models.embedded;

import lombok.Data;
import java.util.Map;

@Data
public class OrderItem {
    private String productId;
    private String variantSku;
    private String title;
    private String image;
    
    // Snapshot price at the time of order
    private Long price;
    private Integer quantity;
    
    // Snapshot attributes
    private Map<String, String> selectedAttributes;
}
