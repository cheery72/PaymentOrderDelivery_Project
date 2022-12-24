package com.project.product.dto.order;

import com.project.product.domain.member.Member;
import com.project.product.domain.order.ApproveStatus;
import com.project.product.domain.order.OrderStatus;
import com.project.product.domain.order.PayType;
import com.project.product.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
public class MemberOrderListResponse {
    private Long id;

    private Long purchaser;

    private int totalPrice;

    private int usePoint;

    private String purchaserMemo;

    private String adminMemo;

    private LocalDateTime paymentDateTime;

    private LocalDateTime approveDateTime;

}
