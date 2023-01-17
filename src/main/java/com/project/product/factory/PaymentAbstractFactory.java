package com.project.product.factory;

import com.project.product.domain.order.PayType;
import com.project.product.domain.payment.CardStatus;
import com.project.product.service.member.MemberService;
import com.project.product.service.payment.CardService;
import com.project.product.service.payment.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class PaymentAbstractFactory {

    private final CardService cardService;
    private final MemberService memberService;

    public PaymentService getPayType(int point, String payType){
        if(0 < point && (String.valueOf(PayType.ALL).equals(payType)
                || String.valueOf(PayType.POINT).equals(payType))){
            return memberService;
        }else if(point == 0 && String.valueOf(PayType.CARD).equals(payType)){
            return cardService;
        }

        throw new IllegalArgumentException();
    }
}
