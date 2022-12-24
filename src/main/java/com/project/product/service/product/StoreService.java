package com.project.product.service.product;

import com.project.product.domain.store.Store;
import com.project.product.dto.product.StoreRegisterRequest;
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
    public void registerFranchiseStore(StoreRegisterRequest storeRegisterRequest){
        storeRepository.save(Store.toStore(storeRegisterRequest));
    }
}
