package com.trading.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SECURITY_POSITION")
public class SecurityPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "INSTRUMENT")
    private String instrument;

    @Column(name = "QUANTITY")
    private int quantity;

    @OneToMany(mappedBy = "securityPosition", fetch = FetchType.EAGER)
    private List<SecurityPositionTradeMapping> tradeMappings;

    @Transient
    private List<String> trades;

    public SecurityPosition(String account, String instrument) {
        this.account = account;
        this.instrument = instrument;
    }

    public List<String> getTrades() {
        if (tradeMappings != null) {
            trades = tradeMappings.stream()
                                  .map(SecurityPositionTradeMapping::getTradeId)
                                  .sorted((o1, o2) -> Long.compare(o2, o1))
                                  .map(String::valueOf)
                                  .collect(Collectors.toList());
        }
        return trades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecurityPosition that = (SecurityPosition) o;
        return quantity == that.quantity &&
               Objects.equals(account, that.account) &&
               Objects.equals(instrument, that.instrument) &&
               Objects.equals(getTrades(), that.getTrades());
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, instrument, quantity, getTrades());
    }

    @Override
    public String toString() {
        return "SecurityPosition{" +
               ", account='" + account + '\'' +
               ", instrument='" + instrument + '\'' +
               ", quantity=" + quantity +
               ", trades=" + getTrades() +
               '}';
    }
}
