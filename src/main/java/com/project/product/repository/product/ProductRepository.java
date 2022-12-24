package com.project.product.repository.product;

import com.project.product.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByOrderId(Long orderId);
}
