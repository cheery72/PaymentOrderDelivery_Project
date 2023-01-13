package com.project.product.service.delivery;

import com.project.product.domain.delivery.Driver;
import com.project.product.domain.delivery.DriverStatus;
import com.project.product.dto.delivery.DriverRegisterRequest;
import com.project.product.dto.delivery.DriverPossibilityListResponse;
import com.project.product.repository.delivery.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DriverService {

    private final DriverRepository driverRepository;

    @Transactional
    public Driver createDriver(DriverRegisterRequest driverRegisterRequest){
        return driverRepository.save(Driver.driverBuilder(driverRegisterRequest));
    }

    public List<DriverPossibilityListResponse> findPossibilityDriver(String city, String gu, String dong){
        List<Driver> drivers =
            driverRepository.findAllByDriverStatusNotInAndAddressCityAndAddressGuAndAddressDong(Collections.singleton(DriverStatus.VACATION),city,gu,dong);

        return DriverPossibilityListResponse.driverWaitingListBuilder(drivers);
    }
}
