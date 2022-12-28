package com.project.product.controller.delivery;

import com.project.product.dto.delivery.DeliveryOrderRegisterRequest;
import com.project.product.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/delivery")
@RestController
@RequiredArgsConstructor
@Slf4j
public class DeliveryController {

    private final DeliveryService deliveryService;


    @PostMapping("/register")
    public ResponseEntity<Object> deliveryOrderRegister(@RequestBody @Valid DeliveryOrderRegisterRequest deliveryOrderRegisterRequest){
        log.info("register order delivery");

        deliveryService.registerDeliveryOrder(deliveryOrderRegisterRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
