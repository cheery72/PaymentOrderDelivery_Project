package com.project.product.service.payment;

import com.project.product.domain.payment.Card;
import com.project.product.dto.CardRegister;
import com.project.product.repository.payment.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardService {

    private final CardRepository cardRepository;

    @Transactional
    public Card registerCard(CardRegister cardRegister){
        Card card = Card.cardBuilder(cardRegister);
        return cardRepository.save(card);
    }
}
