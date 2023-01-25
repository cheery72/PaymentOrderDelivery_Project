package com.project.product.service.payment;

import com.project.product.domain.order.PayType;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.service.coupon.CouponService;
import com.project.product.service.member.MemberService;
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

    private final OrderService orderService;
    private final CouponService couponService;
    private final PointService pointService;
    private final CardService cardService;

    @Transactional
    public void paymentOrder(OrderCreateRequest orderCreateRequest){
        int couponDiscount = couponService.couponActivationCheck(orderCreateRequest.getCouponId());

        LocalDateTime paymentTime = null;

        if(0 < orderCreateRequest.getUsePoint()){
            int restPrice = pointService.pointPayment(orderCreateRequest, couponDiscount);

            if(0 < restPrice) {
                paymentTime = cardService.payment(orderCreateRequest, restPrice);
            }else if(restPrice == 0){
                paymentTime = LocalDateTime.now();
            }
        }else if(orderCreateRequest.getUsePoint() == 0){
            paymentTime = cardService.cardCouponPayment(orderCreateRequest, couponDiscount);
        }

        orderService.createOrder(paymentTime,orderCreateRequest);
    }
}
