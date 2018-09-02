package com.trading.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SECURITY_POSITION_TRADE_MAPPING")
public class SecurityPositionTradeMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "SECURITY_POSITION_ID")
    private long securityPositionId;

    @Column(name = "TRADE_ID")
    private long tradeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SECURITY_POSITION_ID", nullable = false, insertable = false, updatable = false)
    private SecurityPosition securityPosition;

    public SecurityPositionTradeMapping(long securityPositionId, long tradeId) {
        this.securityPositionId = securityPositionId;
        this.tradeId = tradeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecurityPositionTradeMapping mapping = (SecurityPositionTradeMapping) o;
        return securityPositionId == mapping.securityPositionId &&
               tradeId == mapping.tradeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(securityPositionId, tradeId);
    }

    @Override
    public String toString() {
        return "SecurityPositionTradeMapping{" +
               "securityPositionId=" + securityPositionId +
               ", tradeId=" + tradeId +
               '}';
    }
}
