package com.project.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seller;

    private String purchaser;

    private String name;

    private int price;

    private String category;

    private String city;

    private String gu;

    private String dong;

    private String detail;

    private ProductStatus productStatus;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    @Builder
    public Product(Long id, String seller, String purchaser, String name, int price, String category, String city, String gu, String dong, String detail, ProductStatus productStatus, List<ProductImage> images) {
        this.id = id;
        this.seller = seller;
        this.purchaser = purchaser;
        this.name = name;
        this.price = price;
        this.category = category;
        this.city = city;
        this.gu = gu;
        this.dong = dong;
        this.detail = detail;
        this.productStatus = productStatus;
        this.images = images;
    }
}
