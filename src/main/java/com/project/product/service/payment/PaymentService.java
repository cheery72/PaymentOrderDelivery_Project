package com.project.product.service.payment;

import com.project.product.dto.order.OrderCreateRequest;

import java.time.LocalDateTime;

public interface PaymentService<T> {

    T payment(OrderCreateRequest orderCreateRequest, int couponDiscount);
}
