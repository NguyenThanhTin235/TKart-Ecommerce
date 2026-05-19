package com.tkart.ecommerce.models.dto.admin;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApprovalStatusRequest {
    @NotNull(message = "Approval status is required")
    private Boolean approve;
    
    private String reason;
}
