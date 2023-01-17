package com.project.product.domain.product;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public static List<ProductImage> productImageBuilder(List<String> images){
        return images.stream()
                .map(image -> ProductImage.builder()
                    .image(image)
                    .build())
                    .collect(Collectors.toList());
    }
}
