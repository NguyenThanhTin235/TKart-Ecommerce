package com.tkart.ecommerce.models.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttribute {
    private String name;  // e.g. "Color", "Size"
    private String value; // e.g. "Red", "XL"
}
