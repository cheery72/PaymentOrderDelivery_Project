package com.project.product.service.payment;

import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.factory.PaymentAbstractFactory;
import com.project.product.service.coupon.CouponService;
import com.project.product.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderPaymentFacade {

    private final OrderService orderService;
    private final CouponService couponService;
    private final PaymentAbstractFactory paymentAbstractFactory;

    @Transactional
    public void paymentOrder(OrderCreateRequest orderCreateRequest){
        PaymentService service = paymentAbstractFactory.getPayType(orderCreateRequest.getUsePoint(),orderCreateRequest.getPayType());
        int couponDiscount = couponService.couponActivationCheck(orderCreateRequest.getCouponId());

        LocalDateTime paymentTime = service.payment(orderCreateRequest,couponDiscount);

        orderService.createOrder(paymentTime,orderCreateRequest);
    }
}
