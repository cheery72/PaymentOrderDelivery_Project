package com.project.product.controller.order;

import com.project.product.dto.order.OrderCreate;
import com.project.product.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/create-order")
    public ResponseEntity<Object> orderCreate(@RequestBody OrderCreate orderCreate){
        log.info("order create start -----");

        long startTime = System.currentTimeMillis();

        orderService.createOrder(orderCreate);

        log.info("order create end -----");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime-startTime;
        log.info("resultTime = {}",resultTime);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{orderId}/delete-order")
    public ResponseEntity<Object> orderDelete(@PathVariable Long orderId){
        log.info("order delete start ----");

        long startTime = System.currentTimeMillis();

        orderService.deleteOrder(orderId);

        log.info("order delete end ----");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime-startTime;
        log.info("resultTime = {}",resultTime);

        return ResponseEntity
                .noContent()
                .build();

    }

}
