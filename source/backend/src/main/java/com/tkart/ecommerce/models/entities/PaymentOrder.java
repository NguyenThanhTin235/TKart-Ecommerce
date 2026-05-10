package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.enums.PaymentMethod;
import com.tkart.ecommerce.models.enums.PaymentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "payment_orders")
@Data
public class PaymentOrder extends BaseDocument {

    @Indexed
    private String userId;

    private List<String> orderIds;

    private Long subtotal;
    private Long couponDiscount;
    private Long coinDiscount;
    private Long finalPayment;

    private PaymentMethod paymentMethod;

    @Indexed
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    private String transactionId; // ID from payment gateway (e.g. VNPay)
}
