package com.trading.repository;

import com.trading.domain.SecurityPositionTradingMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityPositionTradingMappingRepository extends JpaRepository<SecurityPositionTradingMapping, String> {

    List<SecurityPositionTradingMapping> findByAccountNumber(String accountNumber);
}
