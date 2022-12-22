package com.project.product.repository.product;

import com.project.product.domain.store.Store;
import com.project.product.domain.store.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {
    List<Store> findByCityAndGuAndDongAndStoreStatusIsNot(String city, String gu, String dong, StoreStatus storeStatus);
}
