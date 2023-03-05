package com.project.product.service.delivery;

import com.project.product.domain.delivery.Delivery;
import com.project.product.domain.delivery.Driver;
import com.project.product.domain.order.Order;
import com.project.product.domain.order.OrderStatus;
import com.project.product.dto.delivery.DeliveryCompleteRequest;
import com.project.product.dto.delivery.DeliveryOrderRegisterRequest;
import com.project.product.exception.ClientException;
import com.project.product.exception.ErrorCode;
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
    public Delivery createDeliveryOrder(DeliveryOrderRegisterRequest deliveryOrderRegisterRequest){
        Driver driver = driverRepository.findById(deliveryOrderRegisterRequest.getDriverId())
                .orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_DRIVER));

        Order order = updateOrderStatus(deliveryOrderRegisterRequest.getOrderId(),OrderStatus.SHIPPING);

        Delivery delivery = Delivery.toDelivery(order,driver);

        return deliveryRepository.save(delivery);
    }

    //Todo : 배달 완료 배달 상태 변경
    @Transactional
    public void completeDelivery(DeliveryCompleteRequest deliveryCompleteRequest){
        Delivery delivery = deliveryRepository.findById(deliveryCompleteRequest.getDeliveryId())
                .orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_DELIVERY));

        updateOrderStatus(deliveryCompleteRequest.getOrderId(),OrderStatus.COMPLETED);
        delivery.setDeliveryStatus();
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus){
        Order order = getOrder(orderId);
        order.setOrderStatus(orderStatus);

        return order;
    }


    private Order getOrder(Long orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_ORDER));
    }
}
