package com.tkart.ecommerce.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tkart.ecommerce.models.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private Role role;
    private boolean isVerified;

    private String fullName;

    @JsonProperty("full_name")
    public String getFullNameSnake() {
        return fullName;
    }

    private String avatarUrl;

    @JsonProperty("avatar_url")
    public String getAvatarUrlSnake() {
        return avatarUrl;
    }
}
