package com.trading.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "SECURITY_POSITION")
public class SecurityPosition {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "INSTRUMENT")
    private String instrument;

    @Column(name = "QUANTITY")
    private int quantity;

    @Transient
    List<String> trades;

    public SecurityPosition(String account, String instrument) {
        this.account = account;
        this.instrument = instrument;
    }
}
