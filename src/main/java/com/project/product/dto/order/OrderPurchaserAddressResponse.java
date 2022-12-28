package com.project.product.dto.order;

import com.project.product.domain.order.Order;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderPurchaserAddressResponse {

    private Long orderId;

    private String purchaserCity;

    private String purchaserGu;

    private String purchaserDong;

    private String purchaserDetail;

    @QueryProjection
    @Builder
    public OrderPurchaserAddressResponse(Long orderId, String purchaserCity, String purchaserGu, String purchaserDong, String purchaserDetail) {
        this.orderId = orderId;
        this.purchaserCity = purchaserCity;
        this.purchaserGu = purchaserGu;
        this.purchaserDong = purchaserDong;
        this.purchaserDetail = purchaserDetail;
    }

    public static OrderPurchaserAddressResponse toOrderPurchaserAddressResponse(Order order){
        return OrderPurchaserAddressResponse.builder()
                .orderId(order.getId())
                .purchaserCity(order.getMember().getAddressCity())
                .purchaserGu(order.getMember().getAddressGu())
                .purchaserDong(order.getMember().getAddressDong())
                .purchaserDetail(order.getMember().getAddressDetail())
                .build();
    }
}
