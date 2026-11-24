package com.growmighty.lectures.firstday.tangledmonolith.order.infrastructure;

import com.growmighty.lectures.firstday.tangledmonolith.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
