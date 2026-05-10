package com.tkart.ecommerce.models.embedded;

import com.tkart.ecommerce.models.enums.CoinTransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CoinTransaction {
    private CoinTransactionType type;
    private Long amount;
    private String orderId; // Reference to Order if applicable
    private LocalDateTime date = LocalDateTime.now();
}
