package com.tkart.ecommerce.models.embedded;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TrackingEvent {
    private String status;
    private String rawStatus;
    private String carrierCode;
    private String location;
    private LocalDateTime timestamp;
}
