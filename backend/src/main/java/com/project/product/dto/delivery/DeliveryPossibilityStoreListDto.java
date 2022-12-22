package com.project.product.dto.delivery;

import com.project.product.domain.store.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryPossibilityStoreListDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class DeliveryPossibilityStoreListRequest {
        private String city;

        private String gu;

        private String dong;

        private String detail;

    }

    @NoArgsConstructor
    @Getter
    public static class DeliveryPossibilityStoreListResponse {

        private Long storeId;

        private String city;

        private String gu;

        private String dong;

        private String detail;

        private String status;

        @Builder
        public DeliveryPossibilityStoreListResponse(Long storeId, String city, String gu, String dong, String detail, String status) {
            this.storeId = storeId;
            this.city = city;
            this.gu = gu;
            this.dong = dong;
            this.detail = detail;
            this.status = status;
        }

        public static List<DeliveryPossibilityStoreListResponse> toDeliveryPossibilityStoreListResponse(List<Store> stores){
            return stores.stream()
                    .map(store -> DeliveryPossibilityStoreListResponse.builder()
                            .storeId(store.getId())
                            .city(store.getCity())
                            .gu(store.getGu())
                            .dong(store.getDong())
                            .detail(store.getDetail())
                            .status(String.valueOf(store.getStoreStatus()))
                            .build())
                    .collect(Collectors.toList());

        }

    }
}
