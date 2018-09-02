package com.trading.repository;

import com.trading.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    Trade findByTradeId(Long tradeId);
}
