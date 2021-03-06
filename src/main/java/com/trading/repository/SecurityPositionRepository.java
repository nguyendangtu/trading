package com.trading.repository;

import com.trading.domain.SecurityPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SecurityPositionRepository extends JpaRepository<SecurityPosition, String> {

    SecurityPosition findByAccountAndInstrument(String account, String instrument);

    @Transactional
    @Modifying
    @Query("update SecurityPosition p set p.quantity = p.quantity + :changedQuantity where p.id = :id")
    void updateQuantity(@Param("id") long id, @Param("changedQuantity") int changedQuantity);
}
