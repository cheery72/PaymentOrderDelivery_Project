package com.project.product.dto.delivery;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class DeliveryPossibilityStoreOrderListDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class DeliveryPossibilityStoreOrderListRequest {

        @NotBlank
        private String city;

        @NotBlank
        private String gu;

        @NotBlank
        private String dong;

    }

    @NoArgsConstructor
    @Getter
    public static class DeliveryPossibilityStoreOrderListResponse {

        private Integer orderCount;

        private String storeName;

        private Long storeId;

        private String city;

        private String gu;

        private String dong;

        private String detail;

        @QueryProjection
        @Builder
        public DeliveryPossibilityStoreOrderListResponse(Integer orderCount, String storeName, Long storeId, String city, String gu, String dong, String detail) {
            this.orderCount = orderCount;
            this.storeName = storeName;
            this.storeId = storeId;
            this.city = city;
            this.gu = gu;
            this.dong = dong;
            this.detail = detail;
        }
    }
}
