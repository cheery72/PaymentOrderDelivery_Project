package com.project.product.service.coupon;

import com.project.product.domain.event.Coupon;
import com.project.product.exception.ClientException;
import com.project.product.exception.ErrorCode;
import com.project.product.repository.event.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponService {

    private final CouponRepository couponRepository;

    public int couponActivationCheck(Long couponId){
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_COUPON));

        return coupon.couponExpiryCheck(coupon);
    }
}
