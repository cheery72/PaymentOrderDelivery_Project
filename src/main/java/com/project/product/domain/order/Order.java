package com.project.product.domain.order;

import com.project.product.domain.delivery.Delivery;
import com.project.product.domain.product.Product;
import com.project.product.domain.member.Member;
import com.project.product.domain.store.Store;
import com.project.product.dto.order.OrderCreateRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne(mappedBy = "order")
    private Delivery delivery;

    @Builder
    public Order(Long id, Long purchaser, int totalPrice, int usePoint, String purchaserMemo, String adminMemo, LocalDateTime paymentDateTime, LocalDateTime approveDateTime, ApproveStatus approveStatus, PayType payType, OrderStatus orderStatus, List<Product> products, Member member, Store store, Delivery delivery) {
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
        this.member = member;
        this.store = store;
        this.delivery = delivery;
    }

    public static Order orderBuilder(LocalDateTime paymentDate, OrderCreateRequest orderCreateRequest, List<Product> products){
        return Order.builder()
                .purchaser(orderCreateRequest.getPurchaser())
                .totalPrice(orderCreateRequest.getTotalPrice())
                .usePoint(orderCreateRequest.getUsePoint())
                .purchaserMemo(orderCreateRequest.getPurchaserMemo())
                .adminMemo("결제완료")
                .paymentDateTime(paymentDate)
                .approveDateTime(LocalDateTime.now())
                .approveStatus(ApproveStatus.SUCCESS)
                .payType(PayType.valueOf(orderCreateRequest.getPayType()))
                .orderStatus(OrderStatus.SHIPPING_PREPARATION)
                .products(products)
                .build();
    }

    public void completeOrder(){
        this.orderStatus = OrderStatus.COMPLETED;
    }
}
