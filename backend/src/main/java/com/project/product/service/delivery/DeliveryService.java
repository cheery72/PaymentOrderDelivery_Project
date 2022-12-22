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

    //Todo : 배달원이 현재 지역에서 가능한 모든 배달지 조회
    public List<>

    //Todo : 배달원이 조회한 배달지 상세 조회

    //Todo : 배달원 배달지 등록

    //Todo : 배달 완료 배달 상태 변경

    //Todo : 배달 실패 배달 상태 변경
}
