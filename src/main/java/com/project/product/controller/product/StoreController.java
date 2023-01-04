package com.project.product.controller.product;

import com.project.product.dto.order.StoreDetailResponse;
import com.project.product.dto.product.StoreRegisterRequest;
import com.project.product.service.product.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
@Slf4j
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/register")
    public ResponseEntity<Object> franchiseStoreRegister(@RequestBody @Valid StoreRegisterRequest storeRegisterRequest){

        storeService.registerFranchiseStore(storeRegisterRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{storeId}/detail-store")
    public ResponseEntity<StoreDetailResponse> storeDetailFind(@PathVariable Long storeId){

        return ResponseEntity
                .ok(storeService.findStoreDetail(storeId));
    }
}
