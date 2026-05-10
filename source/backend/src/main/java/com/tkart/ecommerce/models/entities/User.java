package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.enums.AccountStatus;
import com.tkart.ecommerce.models.enums.AuthProvider;
import com.tkart.ecommerce.models.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
@Data
public class User extends BaseDocument {

    private String fullName;

    @Indexed(unique = true)
    private String email;

    private String password;

    private String phone;

    private Role role;

    private AccountStatus status = AccountStatus.ACTIVE;

    private AuthProvider authProvider = AuthProvider.LOCAL;

    private String avatar;
}
