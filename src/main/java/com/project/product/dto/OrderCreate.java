package com.project.product.dto;

import com.project.product.domain.member.Member;
import com.project.product.domain.order.ApproveStatus;
import com.project.product.domain.order.OrderStatus;
import com.project.product.domain.order.PayType;
import com.project.product.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderCreate {

    private Long productId;

    private String purchaser;

    private int totalPrice;

    private int usePoint;

    private String purchaserMemo;

    private String payType;

    private String bankInfo;

}
