package com.project.product.dto.delivery;

import com.project.product.domain.delivery.Driver;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class DriverPossibilityListResponse {

    private Long id;

    private String name;

    private String phone;

    private String status;

    @Builder
    public DriverPossibilityListResponse(Long id, String name, String phone, String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }

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
