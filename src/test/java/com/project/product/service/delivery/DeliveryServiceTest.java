package com.project.product.service.delivery;

import com.project.product.domain.delivery.Delivery;
import com.project.product.domain.delivery.DeliveryStatus;
import com.project.product.domain.delivery.Driver;
import com.project.product.domain.member.Member;
import com.project.product.domain.order.Order;
import com.project.product.dto.delivery.DeliveryOrderRegisterRequest;
import com.project.product.repository.delivery.DeliveryRepository;
import com.project.product.repository.delivery.DriverRepository;
import com.project.product.repository.order.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@EnableAsync
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class DeliveryServiceTest {

    @Mock
    private Order order;

    @Mock
    private Driver driver;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private DeliveryService deliveryService;

    @Test
    @DisplayName("배달 승인 테스트")
    public void registerDeliveryOrder(){
        DeliveryOrderRegisterRequest deliveryOrderRegisterRequest = new DeliveryOrderRegisterRequest(1L,1L);
        Member member = new Member(1L,null,null,null,null,0,0,0,null,
                "시","구","동",null,null,null,null);

        Order order = new Order(1L,1L,15000,3000,"메모",null,null,null,
                null,null,null,null,member,null,null);

        Delivery delivery = Delivery.toDelivery(order, driver);

        when(driverRepository.findById(deliveryOrderRegisterRequest.getDriverId()))
                .thenReturn(Optional.of(driver));

        when(orderRepository.findById(deliveryOrderRegisterRequest.getOrderId()))
                .thenReturn(Optional.of(order));

        when(deliveryRepository.save(any()))
                .thenReturn(delivery);

        Delivery newDelivery = deliveryService.createDeliveryOrder(deliveryOrderRegisterRequest);

        assertEquals(delivery.getCity(),newDelivery.getCity());
        assertEquals(delivery.getDeliveryStatus(), DeliveryStatus.SHIPPING);
    }

}
