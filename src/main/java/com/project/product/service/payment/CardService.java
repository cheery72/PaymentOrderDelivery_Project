package com.project.product.service.payment;

import com.project.product.domain.payment.Card;
import com.project.product.dto.payment.CardRegisterRequest;
import com.project.product.dto.payment.MemberCardListResponse;
import com.project.product.repository.payment.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardService{

    private final CardRepository cardRepository;

    @Transactional
    public Card registerCard(CardRegisterRequest cardRegisterRequest) {
        Card card = Card.cardBuilder(cardRegisterRequest);
        return cardRepository.save(card);
    }

    public List<MemberCardListResponse> findMemberCardList(Long memberId){
        List<Card> cards = cardRepository.findAllByMemberId(memberId);

        return MemberCardListResponse.memberCardListDtoBuilder(cards);
    }
}
