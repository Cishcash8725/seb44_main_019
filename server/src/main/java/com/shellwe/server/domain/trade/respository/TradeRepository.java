package com.shellwe.server.domain.trade.respository;

import com.shellwe.server.domain.member.entity.Member;
import com.shellwe.server.domain.shell.entity.Shell;
import com.shellwe.server.domain.trade.entity.Trade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Query("SELECT t.buyerShell.id FROM Trade t WHERE t.seller.id = :memberId AND t.sellerShell.id = :shellId")
    List<Long> getSellerTradesByShellIdAndMemberId(@Param("memberId") long memberId, @Param("shellId") long shellId);

    @Query("SELECT t.sellerShell.id From Trade t WHERE t.seller.id = :memberId")
    List<Long> getSellerShellsBySellerId(@Param("memberId") long memberId);

    @Query("SELECT t.sellerShell.id, Count(t) From Trade t GROUP BY t.sellerShell.id ORDER BY COUNT(t) DESC")
    List<Object[]> findShellsNotSize();

    @Query("SELECT t.sellerShell.id, Count(t) FROM Trade t GROUP BY t.sellerShell.id ORDER BY COUNT(t) DESC")
    List<Object[]> findShells(Pageable pageable);

    void deleteAllBySellerId(Long id);

    void deleteAllByBuyerId(long id);

    void deleteAllByBuyerShellId(Long buyerShellId);

    void deleteAllBySellerShellId(Long sellerShellId);

    Optional<Trade> findBySellerAndBuyerAndBuyerShellAndSellerShell(Member seller, Member buyer, Shell buyerShell, Shell sellerShell);
}
