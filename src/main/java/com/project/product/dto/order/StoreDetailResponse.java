package com.project.product.dto.order;

import com.project.product.domain.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StoreDetailResponse {

    private Long storeId;

    private String storeName;

    private String city;

    private String gu;

    private String dong;

    private String detail;

    private String phone;

    @Builder
    public StoreDetailResponse(Long storeId, String storeName, String city, String gu, String dong, String detail, String phone) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.city = city;
        this.gu = gu;
        this.dong = dong;
        this.detail = detail;
        this.phone = phone;
    }

    public static StoreDetailResponse toStoreDetailResponse(Store store){
        return StoreDetailResponse.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .city(store.getCity())
                .gu(store.getGu())
                .dong(store.getDong())
                .detail(store.getDetail())
                .phone(store.getPhone())
                .build();
    }

}
