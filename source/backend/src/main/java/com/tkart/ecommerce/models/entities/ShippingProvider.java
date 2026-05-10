package com.tkart.ecommerce.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "shipping_providers")
@Data
public class ShippingProvider extends BaseDocument {

    private String name;
    private String code; // GHTK, GRAB
    private String apiEndpoint;
    private String apiKey; // Should be encrypted
    private boolean isActive = true;
}
