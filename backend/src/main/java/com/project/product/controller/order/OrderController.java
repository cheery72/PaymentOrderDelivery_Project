package com.project.product.controller.order;

import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto;
import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto.DeliveryPossibilityStoreOrderListRequest;
import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto.DeliveryPossibilityStoreOrderListResponse;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/create-order")
    public ResponseEntity<Object> orderCreate(@RequestBody @Valid OrderCreateRequest orderCreateRequest){
        log.info("order create start -----");

        long startTime = System.currentTimeMillis();

        orderService.createOrder(orderCreateRequest);

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

    @GetMapping("/store-order")
    public ResponseEntity<List<DeliveryPossibilityStoreOrderListResponse>> StoreOrderListFind(@RequestBody @Valid DeliveryPossibilityStoreOrderListRequest
                                                                                                            deliveryPossibilityStoreOrderListRequest){
        log.info("find store delivery possibility order list");

        return ResponseEntity
                .ok(orderService.findStoreOrderList(deliveryPossibilityStoreOrderListRequest));
    }

}
