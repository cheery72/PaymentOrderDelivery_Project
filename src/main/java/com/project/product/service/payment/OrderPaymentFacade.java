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

        if(0 < orderCreateRequest.getUsePoint() && (String.valueOf(PayType.ALL).equals(orderCreateRequest.getPayType())
                || String.valueOf(PayType.POINT).equals(orderCreateRequest.getPayType()))){
            int restPrice = pointService.pointPayment(orderCreateRequest, couponDiscount);

            paymentTime = (restPrice > 0) ? cardService.payment(orderCreateRequest, restPrice) : LocalDateTime.now();

        }else if(orderCreateRequest.getUsePoint() == 0 && String.valueOf(PayType.CARD).equals(orderCreateRequest.getPayType())){
            paymentTime = cardService.cardCouponPayment(orderCreateRequest, couponDiscount);
        }

        orderService.createOrder(paymentTime,orderCreateRequest);
    }
}
