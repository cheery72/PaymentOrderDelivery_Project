package com.project.product.dto.order;

import com.project.product.domain.order.Order;
import com.project.product.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class StoreOrderProductNameListResponse {

    private Long orderId;

    private List<String> productName;

    private String orderStatus;

    private Integer productCount;

    @Builder
    public StoreOrderProductNameListResponse(Long orderId, List<String> productName, String orderStatus, Integer productCount) {
        this.orderId = orderId;
        this.productName = productName;
        this.orderStatus = orderStatus;
        this.productCount = productCount;
    }

    public static List<StoreOrderProductNameListResponse> toStoreOrderListResponse(List<Order> orders){
        return orders.stream().map(
                o -> StoreOrderProductNameListResponse.builder()
                        .orderId(o.getId())
                        .productName(o.getProducts().stream().map(Product::getName).collect(Collectors.toList()))
                        .orderStatus(o.getOrderStatus().name())
                        .productCount(o.getProducts().size())
                        .build())
                .collect(Collectors.toList());
    }
}
