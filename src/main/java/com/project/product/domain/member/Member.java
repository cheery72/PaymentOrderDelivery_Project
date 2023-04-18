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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

    public int memberPointPayment(int usePoint, int restPrice){
        //  Todo : 포인트 및 카드 결제
        if (usePoint < restPrice) {
            this.usedPoint += usePoint;
            this.point -= usePoint;
            return restPrice - usePoint;
        // Todo : 할인 받은 금액이 요청한 포인트보다 작을때
        }else {
            this.usedPoint += restPrice;
            this.point -= restPrice;
        }

        return 0;
    }

}
