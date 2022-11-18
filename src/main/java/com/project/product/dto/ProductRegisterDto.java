package com.project.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductRegisterDto {

    private String seller;

    private String name;

    private int price;

    private String category;

    private List<String> images;
}
