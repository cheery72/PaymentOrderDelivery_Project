package com.project.product.repository.member;

import com.project.product.domain.member.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon,Long> {
}
