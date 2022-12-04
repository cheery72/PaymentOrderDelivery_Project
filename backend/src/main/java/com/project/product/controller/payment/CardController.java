package com.project.product.controller.payment;

import com.project.product.dto.payment.CardRegister;
import com.project.product.service.payment.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @PostMapping("/register")
    public ResponseEntity<Object> cardRegister(@RequestBody CardRegister cardRegister){
        log.info("card register start ----");

        cardService.registerCard(cardRegister);

        return ResponseEntity
                .noContent()
                .build();
    }
}
