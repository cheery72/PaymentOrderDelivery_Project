package com.project.product.domain.product;

import com.project.product.domain.member.ShoppingBasket;
import com.project.product.domain.order.Order;
import com.project.product.dto.product.ProductRegisterRequest;
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

    private Long seller;

    private String name;

    private int price;

    private String category;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_basket_id")
    private ShoppingBasket shoppingBasket;

    @Builder
    public Product(Long id, Long seller, String name, int price, String category, ProductStatus productStatus, List<ProductImage> images, Order order, ShoppingBasket shoppingBasket) {
        this.id = id;
        this.seller = seller;
        this.name = name;
        this.price = price;
        this.category = category;
        this.productStatus = productStatus;
        this.images = images;
        this.order = order;
        this.shoppingBasket = shoppingBasket;
    }

    public static Product productBuilder(ProductRegisterRequest productRegisterRequest){
        return Product.builder()
                .seller(productRegisterRequest.getSeller())
                .name(productRegisterRequest.getName())
                .price(productRegisterRequest.getPrice())
                .category(productRegisterRequest.getCategory())
                .images(ProductImage.productImageBuilder(productRegisterRequest.getImages()))
                .productStatus(ProductStatus.VERIFICATION)
                .build();
    }
}
