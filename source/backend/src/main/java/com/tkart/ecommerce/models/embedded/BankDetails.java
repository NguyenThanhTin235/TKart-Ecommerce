package com.tkart.ecommerce.models.embedded;

import lombok.Data;

@Data
public class BankDetails {
    private String accountName;
    private String accountNumber;
    private String bankName;
}
