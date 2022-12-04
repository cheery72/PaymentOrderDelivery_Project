package com.project.product.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductRegisterDto {

    private Long seller;

    private String name;

    private int price;

    private String category;

    private List<String> images;
}
