package com.project.product.domain.order;

import com.project.product.domain.delivery.Delivery;
import com.project.product.domain.product.Product;
import com.project.product.domain.member.Member;
import com.project.product.domain.store.Store;
import com.project.product.dto.order.OrderCreateRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

    public void setOrderStatus(OrderStatus orderStatus) {this.orderStatus = orderStatus; }

}
