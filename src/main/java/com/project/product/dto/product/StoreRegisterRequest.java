package com.project.product.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StoreRegisterRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String city;

    @NotBlank
    private String gu;

    @NotBlank
    private String dong;

    @NotBlank
    private String detail;
}
