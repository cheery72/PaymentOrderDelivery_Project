package com.project.product.domain.member;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static MemberCoupon joinCreateCouponBuilder(Member member){
        return MemberCoupon.builder()
                .name("신규가입 쿠폰")
                .discount(10)
                .member(member)
                .build();
    }
}
