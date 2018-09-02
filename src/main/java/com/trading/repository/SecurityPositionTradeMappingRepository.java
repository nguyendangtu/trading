package com.trading.repository;

import com.trading.domain.SecurityPositionTradeMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityPositionTradeMappingRepository extends JpaRepository<SecurityPositionTradeMapping, String> {

    SecurityPositionTradeMapping findBySecurityPositionIdAndTradeId(long securityPositionId, long tradeId);
}
