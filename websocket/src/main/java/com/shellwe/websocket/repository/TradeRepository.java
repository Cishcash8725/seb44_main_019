package com.shellwe.websocket.repository;

import com.shellwe.websocket.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
