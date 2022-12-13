package com.project.product.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class OrderCreateRequest {

    private List<Long> productId;

    @NotNull
    private Long purchaser;

    @NotNull
    private int totalPrice;

    @NotNull
    private int usePoint;

    private String purchaserMemo;

    @NotBlank
    private String payType;

    @NotNull
    private Long cardId;

    private Long couponId;

}
