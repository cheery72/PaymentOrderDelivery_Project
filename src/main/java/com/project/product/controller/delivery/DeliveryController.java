package com.project.product.controller.delivery;

import com.project.product.dto.delivery.DeliveryCompleteRequest;
import com.project.product.dto.delivery.DeliveryOrderRegisterRequest;
import com.project.product.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/delivery")
@RestController
@RequiredArgsConstructor
@Slf4j
public class DeliveryController {

    private final DeliveryService deliveryService;


    @PostMapping("/register")
    public ResponseEntity<Object> deliveryOrderRegister(
            @RequestBody @Valid DeliveryOrderRegisterRequest deliveryOrderRegisterRequest){

        deliveryService.createDeliveryOrder(deliveryOrderRegisterRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/complete")
    public ResponseEntity<Object> deliveryComplete(
            @RequestBody @Valid DeliveryCompleteRequest deliveryCompleteRequest){

        deliveryService.completeDelivery(deliveryCompleteRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

}
