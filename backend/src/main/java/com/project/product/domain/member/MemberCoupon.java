package com.project.product.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public MemberCoupon(String name, int discount, Member member) {
        this.name = name;
        this.discount = discount;
        this.member = member;
    }

    public static MemberCoupon joinCreateCouponBuilder(Member member){
        return MemberCoupon.builder()
                .name("신규가입 쿠폰")
                .discount(10)
                .member(member)
                .build();
    }
}
