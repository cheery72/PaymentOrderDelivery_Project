package com.project.product.dto.product;


import com.project.product.domain.order.Order;
import com.project.product.domain.product.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class OrderProductListResponse {
    private Long id;

    private String name;

    private int price;

    private String category;

    private String orderStatus;

    private String paymentDateTime;

    public static List<OrderProductListResponse> OrderProductListDtoBuilder(List<Product> products, Order order){
        return products.stream()
                .map(product -> (
                    OrderProductListResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .orderStatus(String.valueOf(order.getOrderStatus()))
                        .price(product.getPrice())
                        .category(product.getCategory())
                        .paymentDateTime((order.getPaymentDateTime()).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)))
                        .build()))
                .collect(Collectors.toList());
    }

    @QueryProjection
    @Builder
    public OrderProductListResponse(Long id, String name, int price, String category, String orderStatus, String paymentDateTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.orderStatus = orderStatus;
        this.paymentDateTime = paymentDateTime;
    }
}
