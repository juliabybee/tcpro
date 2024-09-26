package com.abc.tcpro;


import com.abc.tcpro.TradingCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TradingCardRepository extends JpaRepository<TradingCard, UUID> {
}
