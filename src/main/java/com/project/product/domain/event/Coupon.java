package com.project.product.domain.event;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
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

    public int couponExpiryCheck(Coupon coupon){
        if(CouponStatus.ACTIVATION.equals(coupon.getCouponStatus())){
            return coupon.getDiscount();
        }

        return 1;
    }
}
