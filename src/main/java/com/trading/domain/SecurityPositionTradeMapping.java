package com.trading.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SECURITY_POSITION_TRADE_MAPPING")
public class SecurityPositionTradeMapping {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "SECURITY_POSITION_ID")
    private long securityPositionId;

    @Column(name = "TRADE_ID")
    private long tradeId;

    public SecurityPositionTradeMapping(long securityPositionId, long tradeId) {
        this.securityPositionId = securityPositionId;
        this.tradeId = tradeId;
    }
}
