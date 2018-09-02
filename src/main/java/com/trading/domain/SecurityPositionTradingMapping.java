package com.trading.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SECURITY_POSITION_TRADING_MAPPING")
public class SecurityPositionTradingMapping {
    @Id
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "TRADE_ID")
    private Long tradeId;


}
