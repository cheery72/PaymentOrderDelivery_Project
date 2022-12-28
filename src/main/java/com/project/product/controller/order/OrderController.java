package com.project.product.controller.order;

import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto.DeliveryPossibilityStoreOrderListResponse;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.dto.order.OrderPurchaserAddressResponse;
import com.project.product.dto.order.StoreOrderProductNameListResponse;
import com.project.product.factory.PaymentFactory;
import com.project.product.service.order.OrderService;
import com.project.product.service.payment.PaymentService;
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

    private final PaymentFactory paymentFactory;
    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<Object> orderCreate(@RequestBody @Valid OrderCreateRequest orderCreateRequest){
        log.info("order create start -----");

        PaymentService service = paymentFactory.getPayType(orderCreateRequest.getUsePoint(),orderCreateRequest.getPayType());
        orderService.createOrder(service.payment(orderCreateRequest),orderCreateRequest);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{orderId}/delete-order")
    public ResponseEntity<Object> orderDelete(@PathVariable Long orderId){
        log.info("order delete start ----");

        orderService.deleteOrder(orderId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{city}/{gu}/{dong}/store-order")
    public ResponseEntity<List<DeliveryPossibilityStoreOrderListResponse>> storeOrderListFind(@PathVariable(name = "city") String city,
                                                                                              @PathVariable(name = "gu") String gu,
                                                                                              @PathVariable(name = "dong") String dong){
        log.info("find store delivery possibility order list");

        return ResponseEntity
                .ok(orderService.findStoreOrderList(city,gu,dong));
    }

    @GetMapping("/{storeId}/store-order-list")
    public ResponseEntity<List<StoreOrderProductNameListResponse>> storeOrderProductNameListFind(@PathVariable Long storeId){
        log.info("find store order list");

        return ResponseEntity.ok(orderService.findStoreOrderProductNameList(storeId));
    }

    @GetMapping("/{orderId}/purchaser-address")
    public ResponseEntity<OrderPurchaserAddressResponse> orderPurchaserAddressFind(@PathVariable Long orderId){
        log.info("find order purchaser address");

        return ResponseEntity
                .ok(orderService.findOrderPurchaserAddress(orderId));
    }

}
