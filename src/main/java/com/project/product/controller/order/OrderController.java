package com.project.product.controller.order;

import com.project.product.dto.delivery.DeliveryPossibilityStoreOrderListDto.DeliveryPossibilityStoreOrderListResponse;
import com.project.product.dto.order.OrderCreateRequest;
import com.project.product.dto.order.OrderPurchaserAddressResponse;
import com.project.product.dto.order.StoreOrderProductNameListResponse;
import com.project.product.factory.PaymentAbstractFactory;
import com.project.product.service.order.OrderService;
import com.project.product.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final PaymentAbstractFactory paymentAbstractFactory;
    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<Object> orderCreate(@RequestBody @Valid OrderCreateRequest orderCreateRequest){

        PaymentService service = paymentAbstractFactory.getPayType(orderCreateRequest.getUsePoint(),orderCreateRequest.getPayType());
        orderService.createOrder(service.payment(orderCreateRequest),orderCreateRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{orderId}/delete-order")
    public ResponseEntity<Object> orderDelete(@PathVariable Long orderId){

        orderService.deleteOrder(orderId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
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
