package com.trading.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SECURITY_POSITION")
public class SecurityPosition {
    @Id
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "INSTRUMENT")
    private String instrument;

    @Column(name = "QUANTITY")
    private Integer quantity;
}
