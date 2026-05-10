package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.enums.OtpType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "otp_tokens")
@Data
public class OtpToken extends BaseDocument {

    private String email;
    private String code;
    private OtpType type;

    @Indexed(expireAfterSeconds = 0) // TTL index
    private LocalDateTime expiresAt;

    private boolean used = false;
}
