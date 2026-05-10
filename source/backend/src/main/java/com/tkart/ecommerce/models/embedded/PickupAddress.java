package com.tkart.ecommerce.models.embedded;

import lombok.Data;

@Data
public class PickupAddress {
    private String province;
    private String district;
    private String ward;
    private String detail;
    private String phone;
}
