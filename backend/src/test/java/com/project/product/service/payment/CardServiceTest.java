package com.project.product.service.payment;

import com.project.product.domain.payment.Card;
import com.project.product.domain.payment.CardStatus;
import com.project.product.dto.CardRegister;
import com.project.product.repository.payment.CardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;


    @Test
    @DisplayName("카드 등록")
    public void registerCard(){
        CardRegister cardRegister = new CardRegister("은행");

        Card card = Card.cardBuilder(cardRegister);

        when(cardRepository.save(any()))
                .thenReturn(card);

        Card newCard = cardService.registerCard(cardRegister);

        assertEquals(card.getCardStatus(), CardStatus.TRANSACTION_POSSIBILITY);
        assertEquals(card.getMoney(),50000);
        assertEquals(card.getName(),newCard.getName());

    }

}