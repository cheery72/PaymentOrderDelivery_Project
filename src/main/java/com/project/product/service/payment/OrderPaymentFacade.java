package com.project.product.service.payment;

import com.project.product.domain.order.PayType;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.service.coupon.CouponService;
import com.project.product.service.member.MemberService;
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
    private final MemberService memberService;
    private final CardService cardService;

    @Transactional
    public void paymentOrder(OrderCreateRequest orderCreateRequest){
        int couponDiscount = couponService.couponActivationCheck(orderCreateRequest.getCouponId());
        LocalDateTime paymentTime = null;
        if(0 < orderCreateRequest.getUsePoint() && (String.valueOf(PayType.ALL).equals(orderCreateRequest.getPayType())
                || String.valueOf(PayType.POINT).equals(orderCreateRequest.getPayType()))){
            int restPrice = memberService.pointPayment(orderCreateRequest, couponDiscount);

            if(0 < restPrice) {
                paymentTime = cardService.payment(orderCreateRequest, restPrice);
            }else{
                paymentTime = LocalDateTime.now();
            }
        }else if(orderCreateRequest.getUsePoint() == 0 && String.valueOf(PayType.CARD).equals(orderCreateRequest.getPayType())){
            paymentTime = cardService.cardCouponPayment(orderCreateRequest, couponDiscount);
        }

        orderService.createOrder(paymentTime,orderCreateRequest);
    }
}
