package com.tkart.ecommerce.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "coupons")
@Data
public class Coupon extends BaseDocument {

    @Indexed(unique = true)
    private String code;

    private Double discountPercent;
    private Long minOrderValue;

    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    private boolean isActive;

    private Integer usageLimit; // null = unlimited
    private Integer perUserLimit = 1;
    private Integer usedCount = 0;
}
