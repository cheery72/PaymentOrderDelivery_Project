package com.project.product.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@Getter
public class DriverRegisterRequest {

    @NotBlank
    private final String name;

    @NotBlank
    private final String phone;

    @NotBlank
    private final String addressCity;

    @NotBlank
    private final String addressGu;

    @NotBlank
    private final String addressDong;
}
