package com.project.product.controller.product;

import com.project.product.dto.ProductRegisterDto;
import com.project.product.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Object> productCreate(@RequestBody ProductRegisterDto productRegisterDto){
        log.info("product create start -----");

        productService.registerProduct(productRegisterDto);

        return ResponseEntity
                .noContent()
                .build();
    }
}
