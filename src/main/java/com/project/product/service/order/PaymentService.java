package com.project.product.service.order;

import com.project.product.domain.order.PayType;

public interface PaymentService {
    String paymentTypeCheck(PayType payType);
}
