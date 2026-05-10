package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.enums.PaymentMethod;
import com.tkart.ecommerce.models.enums.TransactionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "transactions")
@Data
public class Transaction extends BaseDocument {

    @Indexed
    private String sellerId;

    private TransactionType type;
    private Long amount;

    @Indexed
    private String paymentOrderId;
    
    private PaymentMethod paymentMethod;
    private String gatewayTransactionId;
}
