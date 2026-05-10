package com.tkart.ecommerce.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "addresses")
@Data
public class Address extends BaseDocument {

    @Indexed
    private String userId; // Reference to User

    private String recipientName;
    private String phone;
    private String province;
    private String district;
    private String ward;
    private String detail;
    private boolean isDefault;
}
