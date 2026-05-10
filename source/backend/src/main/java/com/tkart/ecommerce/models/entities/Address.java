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
    private String userId;

    private String fullName;
    private String phone;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country = "Vietnam";
    private boolean isDefault = false;
}
