package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.embedded.ProductAttribute;
import com.tkart.ecommerce.models.embedded.ProductVariant;
import com.tkart.ecommerce.models.enums.ProductStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "products")
@CompoundIndexes({
    @CompoundIndex(name = "product_filter_idx", def = "{'categoryId': 1, 'status': 1, 'sellingPrice': 1, 'rating': 1}"),
    @CompoundIndex(name = "product_attr_idx", def = "{'attributes.name': 1, 'attributes.value': 1}")
})
@Data
public class Product extends BaseDocument {

    @Indexed
    private String sellerId; // Reference to Seller

    @TextIndexed
    private String name;

    private String title;
    private String slug;

    @TextIndexed
    private String description;

    @Indexed
    private String categoryId;

    @Indexed
    private String categoryL3Id; // Reference to Category Level 3

    private List<String> images;
    
    private List<ProductVariant> variants;

    // Fields for simple product / MVP filtering
    private Long mrp;
    @Indexed
    private Long sellingPrice;
    private Integer stock;
    private List<ProductAttribute> attributes;
    private Double rating = 0.0;
    private Integer reviewsCount = 0;

    @Indexed
    private Long minSellingPrice; // Computed
    private Integer maxDiscountPercent; // Computed
    private Integer totalQuantity; // Computed

    @Indexed
    private ProductStatus status = ProductStatus.PENDING;
}
