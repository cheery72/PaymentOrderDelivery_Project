package com.project.product.repository.order;

import com.project.product.domain.order.Order;
import com.project.product.domain.order.OrderStatus;
import com.project.product.domain.product.Product;
import com.project.product.domain.store.Store;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long>,OrderRepositoryCustom {
}
