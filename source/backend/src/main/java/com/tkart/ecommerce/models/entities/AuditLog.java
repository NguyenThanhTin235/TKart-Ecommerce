package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "audit_logs")
@Data
public class AuditLog extends BaseDocument {

    @Indexed
    private String actorId;
    private Role actorRole;

    private String action; // e.g. "BAN_SELLER"

    @Indexed
    private String targetType; // e.g. "SELLER"
    private String targetId;

    private String details;
}
