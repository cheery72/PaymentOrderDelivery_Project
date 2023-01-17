package com.project.product.service.product;

import com.project.product.domain.store.Store;
import com.project.product.dto.order.StoreDetailResponse;
import com.project.product.dto.product.StoreRegisterRequest;
import com.project.product.exception.ClientException;
import com.project.product.exception.ErrorCode;
import com.project.product.repository.product.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public void createFranchiseStore(StoreRegisterRequest storeRegisterRequest){
        storeRepository.save(Store.toStore(storeRegisterRequest));
    }

    //Todo: 가게 상세 정보 조회
    public StoreDetailResponse findStoreDetail(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_STORE));

        return StoreDetailResponse.toStoreDetailResponse(store);
    }
}
