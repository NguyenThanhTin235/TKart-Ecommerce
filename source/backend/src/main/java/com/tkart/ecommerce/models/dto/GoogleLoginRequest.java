package com.tkart.ecommerce.models.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GoogleLoginRequest {
    @NotBlank(message = "Token ID is required")
    @JsonAlias({"token_id", "tokenId"})
    private String tokenId;
}
