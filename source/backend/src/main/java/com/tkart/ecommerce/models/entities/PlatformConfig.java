package com.tkart.ecommerce.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "platform_configs")
@Data
public class PlatformConfig extends BaseDocument {

    // Singleton document
    private Double coinEarnRate = 0.01; // Example: 1% of order value
    private Double coinValueRate = 1.0; // Example: 1 coin = 1 VND
    private Double platformFeePercent = 5.0; // Example: 5% fee for sellers
}
