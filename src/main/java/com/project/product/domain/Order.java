package com.project.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String purchaser;

    private String addressCity;

    private String addressGu;

    private String addressDong;

    private String addressDetail;

    private int totalPrice;

    private int usePoint;

    private String purchaserMemo;

    private String adminMemo;

    private LocalDateTime paymentDateTime;

    private LocalDateTime approveDateTime;

    private ApproveStatus approveStatus;

    private PayType payType;

    private String bankInfo;

    private OrderStatus orderStatus;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private Product product;

    @Builder
    public Order(Long id, String purchaser, String addressCity, String addressGu, String addressDong, String addressDetail, int totalPrice, int usePoint, String purchaserMemo, String adminMemo, LocalDateTime paymentDateTime, LocalDateTime approveDateTime, ApproveStatus approveStatus, PayType payType, String bankInfo, OrderStatus orderStatus, Product product) {
        this.id = id;
        this.purchaser = purchaser;
        this.addressCity = addressCity;
        this.addressGu = addressGu;
        this.addressDong = addressDong;
        this.addressDetail = addressDetail;
        this.totalPrice = totalPrice;
        this.usePoint = usePoint;
        this.purchaserMemo = purchaserMemo;
        this.adminMemo = adminMemo;
        this.paymentDateTime = paymentDateTime;
        this.approveDateTime = approveDateTime;
        this.approveStatus = approveStatus;
        this.payType = payType;
        this.bankInfo = bankInfo;
        this.orderStatus = orderStatus;
        this.product = product;
    }
}
