package com.project.product.repository.order;

import com.project.product.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
