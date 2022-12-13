package com.project.product.repository.delivery;

import com.project.product.domain.delivery.Driver;
import com.project.product.domain.delivery.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    List<Driver> findAllByDriverStatusNotInAndAddressCityAndAddressGuAndAddressDong(Collection<DriverStatus> driverStatus, String addressCity, String addressGu, String addressDong);
}
