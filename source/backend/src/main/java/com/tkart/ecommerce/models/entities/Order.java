package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.embedded.OrderItem;
import com.tkart.ecommerce.models.embedded.TrackingEvent;
import com.tkart.ecommerce.models.enums.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "orders")
@Data
public class Order extends BaseDocument {

    @Indexed
    private String userId;

    @Indexed
    private String sellerId;

    @Indexed
    private String paymentOrderId;

    private List<OrderItem> orderItems;
    
    // Using Address directly as embedded document for snapshot
    private Address deliveryAddress;

    private Long totalSellingPrice;
    private Long shippingFee;

    private String trackingId;
    private String shippingProvider;
    private List<TrackingEvent> trackingEvents;

    @Indexed
    private OrderStatus status = OrderStatus.PLACED;

    private LocalDateTime deliveredAt;
    private LocalDateTime refundedAt;
}
