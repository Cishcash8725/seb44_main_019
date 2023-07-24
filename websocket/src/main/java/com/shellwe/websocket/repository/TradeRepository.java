package com.shellwe.websocket.repository;

import com.shellwe.websocket.entity.Shell;
import com.shellwe.websocket.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    Optional<Trade> findByBuyerShellAndSellerShell(Shell buyerShell, Shell sellerShell);
}
