package com.tkart.ecommerce.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "deals")
@Data
public class Deal extends BaseDocument {

    @Indexed
    private String categoryId; // Reference to Category

    private Double discountPercent;
    private String title;

    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    private Long viewCount = 0L;
    @Indexed
    private boolean isActive;
}
