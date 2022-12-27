package com.project.product.service.payment;

import com.project.product.domain.event.Coupon;
import com.project.product.domain.payment.Card;
import com.project.product.domain.payment.CardStatus;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.dto.payment.CardRegisterRequest;
import com.project.product.dto.payment.MemberCardListResponse;
import com.project.product.exception.NotPaymentCardException;
import com.project.product.repository.event.CouponRepository;
import com.project.product.repository.payment.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardService implements PaymentService {

    private final CardRepository cardRepository;
    private final CouponRepository couponRepository;

    @Transactional
    public Card registerCard(CardRegisterRequest cardRegisterRequest) {
        Card card = Card.cardBuilder(cardRegisterRequest);
        return cardRepository.save(card);
    }

    public List<MemberCardListResponse> findMemberCardList(Long memberId){
        List<Card> cards = cardRepository.findAllByMemberId(memberId);

        return MemberCardListResponse.memberCardListDtoBuilder(cards);
    }

    @Transactional
    @Override
    public LocalDateTime payment(OrderCreateRequest orderCreateRequest) {
        Coupon coupon = couponRepository.findById(orderCreateRequest.getCouponId())
                .orElseThrow(NoSuchElementException::new);

        int discount = coupon.couponExpiryCheck(coupon);

        Card card = cardRepository.findById(orderCreateRequest.getCardId())
                .orElseThrow(NoSuchElementException::new);

        //Todo: 결제 가능 여부, 결제 금액 확인
        if(CardStatus.TRANSACTION_POSSIBILITY.equals(card.getCardStatus())
                && card.cardPaymentCheck(card.getMoney(),orderCreateRequest.getTotalPrice(),discount)){
            //Todo: 결제 진행
            card.cardPayment(card.getMoney(),orderCreateRequest.getTotalPrice(),discount);

            return LocalDateTime.now();
        }

        throw new NotPaymentCardException("카드 금액이 부족합니다.");
    }

}
