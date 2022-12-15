package com.project.product.service.delivery;

import com.project.product.repository.delivery.DeliveryRepository;
import com.project.product.repository.delivery.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DriverRepository driverRepository;


    //Todo : 배달원과 배달지가 동일한 배달 리스트 조회해서 배달 등록

    //Todo : 배달 완료나 실패시 배달 상태 변경
}
