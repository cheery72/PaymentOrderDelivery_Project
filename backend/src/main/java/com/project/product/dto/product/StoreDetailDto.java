package com.project.product.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class StoreDetailDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class StoreDetailRequest {

        @NotNull
        private Long storeId;

        @NotNull
        private Long driverId;
    }


    @NoArgsConstructor
    @Getter
    public static class StoreDetailResponse{

        private Long storeId;

        private Long driverId;

        private String city;

        private String gu;

        private String dong;

        private String detail;

        private String status;

    }
}
