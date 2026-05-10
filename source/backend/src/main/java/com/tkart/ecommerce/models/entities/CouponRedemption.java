package com.tkart.ecommerce.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "coupon_redemptions")
@CompoundIndex(name = "coupon_user_idx", def = "{'couponId': 1, 'userId': 1}")
@Data
public class CouponRedemption extends BaseDocument {

    private String couponId;
    private String userId;
    private String orderId;
    
    private LocalDateTime usedAt = LocalDateTime.now();
}
