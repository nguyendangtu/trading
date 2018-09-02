package com.trading.repository;

import com.trading.domain.SecurityPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityPositionRepository extends JpaRepository<SecurityPosition, String> {
    SecurityPosition findByAccountNumber(String accountNumber);
}
