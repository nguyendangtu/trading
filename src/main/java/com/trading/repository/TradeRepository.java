package com.trading.repository;

import com.trading.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    Trade findByTradeId(Long tradeId);

    @Query("select distinct t.tradeId, t.accountNumber from Trade t where t.accountNumber=:accountNumber and t.securityIdentifier=:securityIdentifier group by t.accountNumber")
    List<String> getAllTradeIDByAccountNumberAndSecurityIdentifier(@Param("accountNumber") String accountNumber, @Param("securityIdentifier") String securityIdentifier);
}
