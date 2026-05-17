package com.tkart.ecommerce.models.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    @NotBlank(message = "Old password is required")
    @JsonAlias({"old_password", "oldPassword"})
    private String oldPassword;

    @NotBlank(message = "New password is required")
    @JsonAlias({"new_password", "newPassword"})
    private String newPassword;
}
