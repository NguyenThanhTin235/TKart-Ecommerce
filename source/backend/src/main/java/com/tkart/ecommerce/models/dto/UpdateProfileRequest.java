package com.tkart.ecommerce.models.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    @JsonAlias({"full_name", "fullName"})
    private String fullName;

    private String phone;
    private String dob;
    private String gender;
}
