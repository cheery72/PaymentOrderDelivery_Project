package com.project.product.service.member;

import com.project.product.domain.event.Coupon;
import com.project.product.domain.member.Member;
import com.project.product.domain.member.MemberCoupon;
import com.project.product.domain.payment.Card;
import com.project.product.domain.payment.CardStatus;
import com.project.product.dto.member.MemberCreateRequest;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.exception.ClientException;
import com.project.product.exception.ErrorCode;
import com.project.product.repository.event.CouponRepository;
import com.project.product.repository.member.MemberCouponRepository;
import com.project.product.repository.member.MemberRepository;
import com.project.product.repository.payment.CardRepository;
import com.project.product.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberCouponRepository memberCouponRepository;

    @Transactional
    public Member createMember(MemberCreateRequest memberCreateRequest) {
        return memberRepository.save(Member.memberBuilder(memberCreateRequest));
    }

    @Transactional
    @Async
    public void createProvideCoupon(Member member) {
        memberCouponRepository.save(MemberCoupon.joinCreateCouponBuilder(member));
    }
}
