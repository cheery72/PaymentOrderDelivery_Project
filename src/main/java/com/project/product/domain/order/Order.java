package com.project.product.domain.order;

import com.project.product.domain.product.Product;
import com.project.product.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    private UUID id;

    private String purchaser;

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

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Order(UUID id, String purchaser, int totalPrice, int usePoint, String purchaserMemo, String adminMemo, LocalDateTime paymentDateTime, LocalDateTime approveDateTime, ApproveStatus approveStatus, PayType payType, String bankInfo, OrderStatus orderStatus, List<Product> products) {
        this.id = id;
        this.purchaser = purchaser;
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
        this.products = products;
    }
}
