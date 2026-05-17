package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.embedded.BankDetails;
import com.tkart.ecommerce.models.enums.AccountStatus;
import com.tkart.ecommerce.models.enums.AuthProvider;
import com.tkart.ecommerce.models.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private List<Role> roles;

    private boolean isVerified = false;
    private String gstNumber;
    private String storeName;
    private BankDetails bankAccount;
    private List<Address> addresses;

    private AccountStatus status = AccountStatus.ACTIVE;

    private AuthProvider authProvider = AuthProvider.LOCAL;

    private String avatar;
    private String dob;
    private String gender;
}
