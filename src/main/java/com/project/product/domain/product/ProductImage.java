package com.project.product.domain.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public ProductImage(Long id, String image, Product product) {
        this.id = id;
        this.image = image;
        this.product = product;
    }

    public static List<ProductImage> productImageBuilder(List<String> images){
        return images.stream()
                .map(image -> ProductImage.builder()
                    .image(image)
                    .build())
                    .collect(Collectors.toList());
    }
}
