package com.project.product.controller.product;

import com.project.product.dto.delivery.DeliveryPossibilityStoreListDto;
import com.project.product.dto.delivery.DeliveryPossibilityStoreListDto.DeliveryPossibilityStoreListRequest;
import com.project.product.dto.delivery.DeliveryPossibilityStoreListDto.DeliveryPossibilityStoreListResponse;
import com.project.product.service.product.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/stores")
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/possibility")
    public ResponseEntity<List<DeliveryPossibilityStoreListResponse>> possibilityStoreListLocationFind(@RequestBody @Valid DeliveryPossibilityStoreListRequest deliveryPossibilityStoreListRequest){
        log.info("find delivery possibility store list");

        return ResponseEntity
                .ok(storeService.findStoreListPossibilityLocation(deliveryPossibilityStoreListRequest));
    }

}
