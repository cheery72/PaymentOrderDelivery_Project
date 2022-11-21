package com.project.product.domain.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int discount;

    private LocalDateTime startDate;

    private LocalDateTime expiryDate;

    @Enumerated(EnumType.STRING)
    private CouponStatus couponStatus;

    public Coupon(Long id, String name, int discount, LocalDateTime startDate, LocalDateTime expiryDate, CouponStatus couponStatus) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.couponStatus = couponStatus;
    }

    public int couponExpiryCheck(Coupon coupon){
        if(CouponStatus.ACTIVATION.equals(coupon.getCouponStatus())){
            return coupon.getDiscount();
        }

        return 1;
    }
}
