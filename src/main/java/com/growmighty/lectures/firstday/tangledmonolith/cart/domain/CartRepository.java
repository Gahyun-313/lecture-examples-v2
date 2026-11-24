package com.growmighty.lectures.firstday.tangledmonolith.cart.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository {
    Cart save(Cart cart);

    Optional<Cart> findByUserId(Long userId);
}
