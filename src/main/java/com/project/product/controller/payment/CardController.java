package com.project.product.controller.payment;

import com.project.product.dto.payment.CardRegisterRequest;
import com.project.product.service.payment.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @PostMapping("/register")
    public ResponseEntity<Object> cardRegister(@RequestBody @Valid CardRegisterRequest cardRegisterRequest){

        cardService.registerCard(cardRegisterRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
