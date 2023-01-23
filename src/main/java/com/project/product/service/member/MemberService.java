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
public class MemberService implements PaymentService {

    private final MemberRepository memberRepository;
    private final MemberCouponRepository memberCouponRepository;
    private final CardRepository cardRepository;

    @Transactional
    public Member createMember(MemberCreateRequest memberCreateRequest) {
        return memberRepository.save(Member.memberBuilder(memberCreateRequest));
    }

    @Transactional
    @Async
    public void createProvideCoupon(Member member) {
        memberCouponRepository.save(MemberCoupon.joinCreateCouponBuilder(member));
    }

    @Transactional
    public int pointPayment(OrderCreateRequest orderCreateRequest, int discount) {

        Member member = memberRepository.findById(orderCreateRequest.getPurchaser())
                .orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_MEMBER));

        if(memberPointCheck(orderCreateRequest.getUsePoint(), member.getPoint())){
            return  member.memberPointPayment(orderCreateRequest.getTotalPrice(), orderCreateRequest.getUsePoint(), discount);
        }
        throw new ClientException(ErrorCode.REJECT_POINT_PAYMENT);

    }

    private boolean memberPointCheck(int userPoint, int memberAvailablePoint){
        return userPoint <= memberAvailablePoint;
    }


    @Override
    public LocalDateTime payment(OrderCreateRequest orderCreateRequest, int couponDiscount) {
        return null;
    }
}
