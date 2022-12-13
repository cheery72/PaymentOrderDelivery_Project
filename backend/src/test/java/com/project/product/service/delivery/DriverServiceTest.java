package com.project.product.service.delivery;

import com.project.product.domain.delivery.Driver;
import com.project.product.domain.delivery.DriverStatus;
import com.project.product.dto.delivery.DriverRegisterRequest;
import com.project.product.dto.delivery.DriverPossibilityListResponse;
import com.project.product.repository.delivery.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class DriverServiceTest {

    @Mock
    private Driver driver;

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @Test
    public void registerDriver(){
        DriverRegisterRequest requestBuilder = DriverRegisterRequest.builder()
                .name("김경민")
                .phone("010-0000-0000")
                .addressCity("시")
                .addressGu("구")
                .addressDong("동")
                .build();

        Driver driver = Driver.driverBuilder(requestBuilder);

        when(driverRepository.save(any()))
                .thenReturn(driver);

        Driver saveDriver = driverService.registerDriver(requestBuilder);

        assertEquals(saveDriver.getDriverStatus(), DriverStatus.WAITING);
        assertEquals(saveDriver.getName(),requestBuilder.getName());
        assertEquals(saveDriver.getPhone(),requestBuilder.getPhone());
    }

    @Test
    public void findWaitingDriver(){
        String city = "시";
        String gu = "구";
        String dong = "동";

        Driver driver = Driver.builder()
                .id(1L)
                .name("김경민")
                .phone("010-0000-0000")
                .addressCity("시")
                .addressGu("구")
                .addressDong("동")
                .build();

        when(driverRepository
            .findAllByDriverStatusNotInAndAddressCityAndAddressGuAndAddressDong(Collections.singleton(DriverStatus.VACATION),city,gu,dong))
            .thenReturn(List.of(driver));

        List<DriverPossibilityListResponse> waitingDriver = driverService.findPossibilityDriver(city,gu,dong);

        assertEquals(waitingDriver.get(0).getName(),driver.getName());
        assertEquals(waitingDriver.get(0).getPhone(),driver.getPhone());
    }
}
