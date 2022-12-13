package com.project.product.service.delivery;

import com.project.product.domain.delivery.Driver;
import com.project.product.dto.delivery.DriverRegisterRequest;
import com.project.product.repository.delivery.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DriverService {

    private final DriverRepository driverRepository;

    @Transactional
    public Driver registerDriver(DriverRegisterRequest driverRegisterRequest){
        return driverRepository.save(Driver.driverBuilder(driverRegisterRequest));
    }
}
