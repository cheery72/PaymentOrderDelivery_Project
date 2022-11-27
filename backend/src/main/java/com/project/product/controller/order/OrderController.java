package com.project.product.controller.order;

import com.project.product.domain.order.Order;
import com.project.product.dto.OrderCreate;
import com.project.product.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<Object> orderCreate(@RequestBody OrderCreate orderCreate){
        log.info("order create start -----");

        orderService.createOrder(orderCreate);

        return ResponseEntity
                .noContent()
                .build();
    }
}
