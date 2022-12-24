package com.project.product.domain.member;

import com.project.product.domain.order.Order;
import com.project.product.domain.payment.Card;
import com.project.product.dto.member.MemberCreateRequest;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Builder
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

    public void joinCoupon(MemberCoupon memberCoupon) {
        this.memberCoupon = List.of(memberCoupon);
    }

    public static Member memberBuilder(MemberCreateRequest memberCreateRequest){
        return Member.builder()
                .email(memberCreateRequest.getEmail())
                .password(memberCreateRequest.getPassword())
                .name(memberCreateRequest.getName())
                .image(memberCreateRequest.getImage())
                .memberStatus(MemberStatus.MAINTENANCE)
                .addressCity(memberCreateRequest.getAddressCity())
                .addressGu(memberCreateRequest.getAddressGu())
                .addressDong(memberCreateRequest.getAddressDong())
                .addressDetail(memberCreateRequest.getAddressDetail())
                .build();
    }

    public boolean memberPointCheck(int orderPoint, int memberPoint){
        if(orderPoint <= memberPoint){
            return true;
        }
        return false;
    }

    public void memberPointPayment(int orderPoint, Member member,int discount){
        this.point = member.getPoint()-(orderPoint - (orderPoint * (discount / 100)));
        this.usedPoint = member.usedPoint+orderPoint;
    }

}
