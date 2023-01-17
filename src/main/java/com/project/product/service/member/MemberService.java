package com.project.product.service.member;

import com.project.product.domain.event.Coupon;
import com.project.product.domain.member.Member;
import com.project.product.domain.member.MemberCoupon;
import com.project.product.domain.payment.Card;
import com.project.product.domain.payment.CardStatus;
import com.project.product.dto.member.MemberCreateRequest;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.exception.NotFoundMemberException;
import com.project.product.exception.NotPaymentCardException;
import com.project.product.exception.NotPaymentPointException;
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
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements PaymentService {

    private final MemberRepository memberRepository;
    private final MemberCouponRepository memberCouponRepository;
    private final CouponRepository couponRepository;
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
    @Override
    public LocalDateTime payment(OrderCreateRequest orderCreateRequest) {
        Coupon coupon = couponRepository.findById(orderCreateRequest.getCouponId())
                .orElseThrow(NoSuchElementException::new);

        int discount = coupon.couponExpiryCheck(coupon);

        Member member = memberRepository.findById(orderCreateRequest.getPurchaser())
                .orElseThrow(() -> new NotFoundMemberException("요청한 멤버를 찾을 수 없습니다."));
        if(orderCreateRequest.getUsePoint() <= member.getPoint()){
            int restPrice = member.memberPointPayment(orderCreateRequest.getTotalPrice(), orderCreateRequest.getUsePoint(), discount);

            if(0 < restPrice) {
                paymentCardOrder(orderCreateRequest.getCardId(), restPrice);
            }
            return LocalDateTime.now();
        }
        throw new NotPaymentPointException("요청한 포인트가 소지한 포인트보다 높습니다.");

    }

    @Transactional
    public void paymentCardOrder(Long cardId, int totalPrice) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(NoSuchElementException::new);

        if(CardStatus.TRANSACTION_POSSIBILITY.equals(card.getCardStatus())
                && totalPrice <= card.getMoney()){
            card.cardPayment(totalPrice,0);
            return;
        }
        throw new NotPaymentCardException("카드 금액이 부족합니다.");
    }
}
