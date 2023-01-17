package com.project.product.dto.delivery;

import com.project.product.domain.delivery.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DriverPossibilityListResponse {

    private Long id;

    private String name;

    private String phone;

    private String status;

    public static List<DriverPossibilityListResponse> driverWaitingListBuilder(List<Driver> drivers){
        return drivers.stream()
                .map(driver -> DriverPossibilityListResponse.builder()
                        .id(driver.getId())
                        .name(driver.getName())
                        .phone(driver.getPhone())
                        .status(String.valueOf(driver.getDriverStatus()))
                        .build())
                        .collect(Collectors.toList());
    }
}
