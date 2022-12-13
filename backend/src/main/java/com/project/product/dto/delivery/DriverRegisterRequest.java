package com.project.product.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class
DriverRegisterRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;
}
