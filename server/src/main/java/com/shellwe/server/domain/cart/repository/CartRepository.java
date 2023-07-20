package com.shellwe.server.domain.cart.repository;

import com.shellwe.server.domain.cart.entity.Cart;
import com.shellwe.server.domain.member.entity.Member;
import com.shellwe.server.domain.shell.entity.Shell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    void deleteAllByShellId(long shellId);

    void deleteAllByOwnerId(long memberId);

    List<Cart> findAllByOwnerId(long memberId);

    Optional<Cart> findByOwnerAndShell(Member member, Shell shell);
}
