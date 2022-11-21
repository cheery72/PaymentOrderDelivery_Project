package com.project.product.domain.member;

import com.project.product.domain.order.Order;
import com.project.product.domain.payment.Card;
import com.project.product.domain.payment.CardStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table
@Entity
@Getter
@NoArgsConstructor
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String image;

    private int point;

    private int usedPoint;

    private int receivePoint;

    private String addressCity;

    private String addressGu;

    private String addressDong;

    private String addressDetail;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MemberCoupon> memberCoupon = new ArrayList<>();

    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private ShoppingBasket shoppingBasket;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public Member(Long id, String email, String password, String name, String image, int point, int usedPoint, int receivePoint, MemberStatus memberStatus, String addressCity, String addressGu, String addressDong, String addressDetail, List<MemberCoupon> memberCoupon, ShoppingBasket shoppingBasket, List<Order> orders) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.image = image;
        this.point = point;
        this.usedPoint = usedPoint;
        this.receivePoint = receivePoint;
        this.memberStatus = memberStatus;
        this.addressCity = addressCity;
        this.addressGu = addressGu;
        this.addressDong = addressDong;
        this.addressDetail = addressDetail;
        this.memberCoupon = memberCoupon;
        this.shoppingBasket = shoppingBasket;
        this.orders = orders;
    }

    public boolean memberPointCheck(int orderPoint, int memberPoint){
        if(orderPoint <= memberPoint){
            return true;
        }
        return false;
    }

    public void memberPointChange(int orderPoint, Member member,int discount){
        this.point = member.getPoint()-(orderPoint - (orderPoint / 10 * discount));
        this.usedPoint = member.usedPoint+orderPoint;
    }

}
