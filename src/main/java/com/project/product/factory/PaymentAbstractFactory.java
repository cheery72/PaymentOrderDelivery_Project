package com.project.product.factory;

import com.project.product.domain.order.PayType;
import com.project.product.service.payment.CardService;
import com.project.product.service.payment.PaymentService;
import com.project.product.service.point.PointService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class PaymentAbstractFactory {

    private final CardService cardService;
    private final PointService pointService;

    public PaymentService<Object> getPayType(String payType){
        if (String.valueOf(PayType.POINT).equals(payType)){
            return pointService;
        }else if (String.valueOf(PayType.CARD).equals(payType)){
            return cardService;
        }

        throw new IllegalArgumentException();
    }
}
