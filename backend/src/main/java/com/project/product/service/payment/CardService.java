package com.project.product.service.payment;

import com.project.product.domain.payment.Card;
import com.project.product.dto.payment.CardRegister;
import com.project.product.dto.payment.MemberCardListDto;
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
    public Card registerCard(CardRegister cardRegister) {
        Card card = Card.cardBuilder(cardRegister);
        return cardRepository.save(card);
    }

    public List<MemberCardListDto> findMemberCardList(Long memberId){
        List<Card> cards = cardRepository.findAllByMemberId(memberId);

        return MemberCardListDto.MemberCardListDtoBuilder(cards);
    }
}
