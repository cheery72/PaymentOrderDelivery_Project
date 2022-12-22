package com.project.product.service.product;

import com.project.product.domain.store.Store;
import com.project.product.domain.store.StoreStatus;
import com.project.product.dto.delivery.DeliveryPossibilityStoreListDto.DeliveryPossibilityStoreListRequest;
import com.project.product.dto.delivery.DeliveryPossibilityStoreListDto.DeliveryPossibilityStoreListResponse;
import com.project.product.repository.product.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    //Todo : 배달원이 현재 지역에서 가능한 모든 배달지 조회
    public List<DeliveryPossibilityStoreListResponse> findStoreListPossibilityLocation(DeliveryPossibilityStoreListRequest deliveryPossibilityStoreListRequest){
        List<Store> storeList = storeRepository.findByCityAndGuAndDongAndStoreStatusIsNot(deliveryPossibilityStoreListRequest.getCity(), deliveryPossibilityStoreListRequest.getGu(),
                deliveryPossibilityStoreListRequest.getDong(), StoreStatus.PRODUCT_TAKE_OVER);

        return DeliveryPossibilityStoreListResponse.toDeliveryPossibilityStoreListResponse(storeList);

    }
}
