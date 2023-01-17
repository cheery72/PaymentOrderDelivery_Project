package com.project.product.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class DeliveryCompleteRequest {

    @NotNull
    private final Long deliveryId;

    @NotNull
    private final Long orderId;

}
