package com.project.product.service.delivery;

import com.project.product.domain.delivery.Delivery;
import com.project.product.domain.delivery.Driver;
import com.project.product.domain.order.Order;
import com.project.product.dto.delivery.DeliveryCompleteRequest;
import com.project.product.dto.delivery.DeliveryOrderRegisterRequest;
import com.project.product.dto.order.OrderPurchaserAddressResponse;
import com.project.product.exception.NotFoundDeliveryException;
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

        Order order = orderValidation(deliveryOrderRegisterRequest.getOrderId());

        Delivery delivery = Delivery.toDelivery(order,driver);

        return deliveryRepository.save(delivery);
    }

    //Todo : 배달 완료 배달 상태 변경
    @Transactional
    public void completeDelivery(DeliveryCompleteRequest deliveryCompleteRequest){
        Delivery delivery = deliveryRepository.findById(deliveryCompleteRequest.getDeliveryId())
                .orElseThrow(() -> new NotFoundDeliveryException("해당 배달을 찾을 수 없습니다."));

        Order order = orderValidation(deliveryCompleteRequest.getOrderId());
        order.completeOrder();
        delivery.setDeliveryStatus();
    }

    private Order orderValidation(Long orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundOrderException("해당 주문을 찾을 수 없습니다."));

    }
}
