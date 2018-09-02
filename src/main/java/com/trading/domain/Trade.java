package com.trading.domain;

import com.trading.enums.OPERATION_ENUM;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TRADE")
public class Trade {
    @Id
    @Column(name = "TRADE_ID")
    private Long tradeId;

    @Column(name = "TRADE_VERSION")
    private Integer tradeVersion;

    @Column(name = "SECURITY_IDENTIFIER")
    private String securityIdentifier;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "DIRECTION")
    private Character direction;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Enumerated
    @Column(name = "OPERATION")
    private OPERATION_ENUM operation;

    public Trade() {
    }

    public Trade(Long tradeId, Integer tradeVersion, String securityIdentifier, Integer quantity, Character direction, String accountNumber, OPERATION_ENUM operation) {
        this.tradeId = tradeId;
        this.tradeVersion = tradeVersion;
        this.securityIdentifier = securityIdentifier;
        this.quantity = quantity;
        this.direction = direction;
        this.accountNumber = accountNumber;
        this.operation = operation;
    }

}
