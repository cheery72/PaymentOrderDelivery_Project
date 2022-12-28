package com.project.product.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeliveryOrderRegisterRequest {

    @NotNull
    private Long orderId;

    @NotNull
    private Long driverId;
}
