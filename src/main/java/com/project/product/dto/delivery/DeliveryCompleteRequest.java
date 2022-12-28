package com.project.product.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DeliveryCompleteRequest {

    @NotNull
    private Long deliveryId;

    @NotNull
    private Long orderId;
}
