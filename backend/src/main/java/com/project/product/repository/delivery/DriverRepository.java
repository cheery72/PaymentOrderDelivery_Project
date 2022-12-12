package com.project.product.repository.delivery;

import com.project.product.domain.delivery.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long> {
}
