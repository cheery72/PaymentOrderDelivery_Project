package com.project.product.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CardRegisterRequest {

    @NotNull
    private Long memberId;

    @NotBlank
    private String name;

}
