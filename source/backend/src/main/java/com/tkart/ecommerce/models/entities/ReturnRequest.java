package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.enums.ReturnStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "return_requests")
@Data
public class ReturnRequest extends BaseDocument {

    @Indexed(unique = true)
    private String orderId; // One return request per order

    private String userId;
    private String sellerId;

    private String reason;
    private List<String> evidences; // URLs to images/videos

    @Indexed
    private ReturnStatus status = ReturnStatus.REQUESTED;

    private String sellerNote;
    private String adminNote;
}
