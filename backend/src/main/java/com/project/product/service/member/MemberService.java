package com.project.product.service.member;

import com.project.product.domain.member.Member;
import com.project.product.domain.member.MemberCoupon;
import com.project.product.dto.member.MemberCreateRequest;
import com.project.product.repository.member.MemberCouponRepository;
import com.project.product.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberCouponRepository memberCouponRepository;

    @Transactional
    public Member joinMember(MemberCreateRequest memberCreateRequest) {
        return memberRepository.save(Member.memberBuilder(memberCreateRequest));
    }

    @Transactional
    @Async
    public void joinProvideCoupon(Member member) throws InterruptedException {
        memberCouponRepository.save(MemberCoupon.joinCreateCouponBuilder(member));
    }
}
