package com.tkart.ecommerce.models.entities;

import com.tkart.ecommerce.models.embedded.CoinTransaction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "coin_wallets")
@Data
public class CoinWallet extends BaseDocument {

    @Indexed(unique = true)
    private String userId;

    private Long balance = 0L;

    private List<CoinTransaction> transactions;
}
