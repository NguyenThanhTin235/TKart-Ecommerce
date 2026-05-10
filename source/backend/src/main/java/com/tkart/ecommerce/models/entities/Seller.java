package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.embedded.BankDetails;
import com.tkart.ecommerce.models.embedded.PickupAddress;
import com.tkart.ecommerce.models.enums.SellerStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "sellers")
@Data
public class Seller extends BaseDocument {

    @Indexed(unique = true)
    private String userId; // FK 1-1 to User

    private String shopName;
    private String gstNumber;

    private BankDetails bankDetails;
    private PickupAddress pickupAddress;

    private String logo;
    private String banner;

    private SellerStatus status = SellerStatus.PENDING_VERIFICATION;
}
