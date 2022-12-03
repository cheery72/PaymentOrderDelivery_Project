package com.project.product.service.payment;

import com.project.product.domain.member.Member;
import com.project.product.domain.payment.Card;
import com.project.product.domain.payment.CardStatus;
import com.project.product.dto.CardRegister;
import com.project.product.dto.MemberCardListDto;
import com.project.product.repository.payment.CardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class CardServiceTest {

    @Mock
    private Member member;

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;


    @Test
    @DisplayName("카드 등록")
    public void registerCard(){
        CardRegister cardRegister = new CardRegister(1L,"은행");

        Card card = Card.cardBuilder(cardRegister);

        when(cardRepository.save(any()))
                .thenReturn(card);

        Card newCard = cardService.registerCard(cardRegister);

        assertEquals(card.getCardStatus(), CardStatus.TRANSACTION_POSSIBILITY);
        assertEquals(card.getMoney(),50000);
        assertEquals(card.getName(),newCard.getName());

    }

    @Test
    @DisplayName("멤버 카드 조회")
    public void findMemberCardList() {
        Long memberId = 1L;
        List<Card> cards = new ArrayList<>();
        Card card = new Card();
        card.setCardStatus(CardStatus.TRANSACTION_POSSIBILITY);
        card.setName("은행1");
        cards.add(card);
        Card card2 = new Card();
        card2.setCardStatus(CardStatus.TRANSACTION_STOP);
        card2.setName("은행2");
        cards.add(card2);

        when(cardRepository.findAllByMemberId(memberId))
                .thenReturn(cards);

        List<MemberCardListDto> newMemberCardList = cardService.findMemberCardList(memberId);

        assertEquals(newMemberCardList.get(0).getStatus(),String.valueOf(CardStatus.TRANSACTION_POSSIBILITY));
        assertEquals(newMemberCardList.get(1).getStatus(),String.valueOf(CardStatus.TRANSACTION_STOP));
        assertEquals(newMemberCardList.get(0).getName(),card.getName());
        assertEquals(newMemberCardList.get(1).getName(),card2.getName());
    }
}