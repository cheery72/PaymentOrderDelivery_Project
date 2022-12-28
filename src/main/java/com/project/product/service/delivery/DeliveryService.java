package com.project.product.service.delivery;

import com.project.product.domain.delivery.Delivery;
import com.project.product.domain.delivery.Driver;
import com.project.product.domain.order.Order;
import com.project.product.dto.delivery.DeliveryOrderRegisterRequest;
import com.project.product.dto.order.OrderPurchaserAddressResponse;
import com.project.product.exception.NotFoundDriverException;
import com.project.product.exception.NotFoundOrderException;
import com.project.product.repository.delivery.DeliveryRepository;
import com.project.product.repository.delivery.DriverRepository;
import com.project.product.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DriverRepository driverRepository;
    private final OrderRepository orderRepository;

    //Todo: 배달 승인
    @Transactional
    public Delivery registerDeliveryOrder(DeliveryOrderRegisterRequest deliveryOrderRegisterRequest){
        Driver driver = driverRepository.findById(deliveryOrderRegisterRequest.getDriverId())
                .orElseThrow(() -> new NotFoundDriverException("해당 배달원을 찾을 수 없습니다."));

        Order order = orderRepository.findById(deliveryOrderRegisterRequest.getOrderId())
                .orElseThrow(() -> new NotFoundOrderException("해당 주문을 찾을 수 없습니다."));

        Delivery delivery = Delivery.toDelivery(order,driver);

        return deliveryRepository.save(delivery);
    }


    //Todo : 배달원이 현재 지역에서 가능한 모든 배달지 조회

    //Todo : 배달원이 조회한 배달지 상세 조회

    //Todo : 배달원 배달지 등록

    //Todo : 배달 완료 배달 상태 변경

    //Todo : 배달 실패 배달 상태 변경
}
