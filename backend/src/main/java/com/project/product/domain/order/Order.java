package com.project.product.domain.order;

import com.project.product.domain.payment.Card;
import com.project.product.domain.product.Product;
import com.project.product.domain.member.Member;
import com.project.product.dto.OrderCreate;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long purchaser;

    private int totalPrice;

    private int usePoint;

    private String purchaserMemo;

    private String adminMemo;

    private LocalDateTime paymentDateTime;

    private LocalDateTime approveDateTime;

    @Enumerated(EnumType.STRING)
    private ApproveStatus approveStatus;

    @Enumerated(EnumType.STRING)
    private PayType payType;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Order(Long id, Long purchaser, int totalPrice, int usePoint, String purchaserMemo, String adminMemo, LocalDateTime paymentDateTime, LocalDateTime approveDateTime, ApproveStatus approveStatus, PayType payType,  OrderStatus orderStatus, List<Product> products) {
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
        this.orderStatus = orderStatus;
        this.products = products;
    }

    public static Order orderBuilder(LocalDateTime paymentDate,OrderCreate orderCreate, List<Product> products){
        return Order.builder()
                .purchaser(orderCreate.getPurchaser())
                .totalPrice(orderCreate.getTotalPrice())
                .usePoint(orderCreate.getUsePoint())
                .purchaserMemo(orderCreate.getPurchaserMemo())
                .adminMemo("결제완료")
                .paymentDateTime(paymentDate)
                .approveDateTime(LocalDateTime.now())
                .approveStatus(ApproveStatus.SUCCESS)
                .payType(PayType.valueOf(orderCreate.getPayType()))
                .orderStatus(OrderStatus.SHIPPING_PREPARATION)
                .products(products)
                .build();
    }
}
