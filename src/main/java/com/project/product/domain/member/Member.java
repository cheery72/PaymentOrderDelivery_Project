package com.project.product.domain.member;

import com.project.product.domain.order.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    private UUID id;

    private String email;

    private String password;

    private String name;

    private String image;

    private int point;

    private int usedPoint;

    private int receivePoint;

    private MemberStatus memberStatus;

    private String addressCity;

    private String addressGu;

    private String addressDong;

    private String addressDetail;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MemberCoupon> memberCoupon;

    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private ShoppingBasket shoppingBasket;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();


}
