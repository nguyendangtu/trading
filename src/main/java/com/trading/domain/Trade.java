package com.trading.domain;

import com.trading.enums.OPERATION_ENUM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Trade trade = (Trade) o;
        return tradeId == trade.tradeId &&
               version == trade.version &&
               quantity == trade.quantity &&
               Objects.equals(securityIdentifier, trade.securityIdentifier) &&
               Objects.equals(direction, trade.direction) &&
               Objects.equals(account, trade.account) &&
               operation == trade.operation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId, version, securityIdentifier, quantity, direction, account, operation);
    }
}
