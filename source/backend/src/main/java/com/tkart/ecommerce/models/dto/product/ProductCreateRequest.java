package com.tkart.ecommerce.models.dto.product;

import com.tkart.ecommerce.models.embedded.ProductAttribute;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProductCreateRequest {
    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @NotBlank(message = "Category ID is required")
    private String categoryId;

    private List<String> images;

    @NotNull(message = "MRP is required")
    @Min(value = 0, message = "MRP must be positive")
    private Long mrp;

    @NotNull(message = "Selling price is required")
    @Min(value = 0, message = "Selling price must be positive")
    private Long sellingPrice;

    @Min(value = 0, message = "Stock must be positive")
    private Integer stock;

    private List<ProductAttribute> attributes;
}
