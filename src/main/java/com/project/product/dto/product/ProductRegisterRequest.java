package com.project.product.dto.product;

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
public class ProductRegisterRequest {

    @NotNull
    private Long seller;

    @NotBlank
    private String name;

    @NotNull
    private int price;

    @NotBlank
    private String category;

    private List<String> images;
}
