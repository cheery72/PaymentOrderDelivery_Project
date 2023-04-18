package com.project.product.service.payment;

import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.factory.PaymentAbstractFactory;
import com.project.product.service.coupon.CouponService;
import com.project.product.service.order.OrderService;
import com.project.product.service.point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderPaymentFacade {

    private final PaymentAbstractFactory paymentAbstractFactory;
    private final OrderService orderService;
    private final CouponService couponService;
    private final CardService cardService;

    @Transactional
    public void paymentOrder(OrderCreateRequest orderCreateRequest){
        int restPrice = couponService.couponDiscount(orderCreateRequest.getCouponId(),orderCreateRequest.getTotalPrice());

        LocalDateTime paymentTime = null;
        PaymentService<Object> payType = paymentAbstractFactory.getPayType(orderCreateRequest.getPayType());
        Object payment = payType.payment(orderCreateRequest, restPrice);

        if (payment instanceof Integer){
            int paymentAmount = Integer.parseInt(String.valueOf(payment));
            paymentTime = 0 < paymentAmount ? cardService.payment(orderCreateRequest, paymentAmount) : LocalDateTime.now();
        }else if(payment instanceof LocalDateTime){
            paymentTime = LocalDateTime.parse(String.valueOf(payment));
        }

        orderService.createOrder(paymentTime,orderCreateRequest);
    }
}
