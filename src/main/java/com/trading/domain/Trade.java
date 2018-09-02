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
    private long tradeId;

    @Column(name = "VERSION")
    private int version;

    @Column(name = "SECURITY_IDENTIFIER")
    private String securityIdentifier;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "DIRECTION")
    private Character direction;

    @Column(name = "ACCOUNT")
    private String account;

    @Enumerated
    @Column(name = "OPERATION")
    private OPERATION_ENUM operation;

    public Trade() {
    }

    public Trade(Long tradeId, int version, String securityIdentifier, int quantity, Character direction, String account, OPERATION_ENUM operation) {
        this.tradeId = tradeId;
        this.version = version;
        this.securityIdentifier = securityIdentifier;
        this.quantity = quantity;
        this.direction = direction;
        this.account = account;
        this.operation = operation;
    }

}
