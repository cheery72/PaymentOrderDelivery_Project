package com.project.product.controller.delivery;

import com.project.product.dto.delivery.DriverRegisterRequest;
import com.project.product.service.delivery.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/driver")
@Slf4j
@RestController
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity<Object> driverRegister(@RequestBody DriverRegisterRequest driverRegisterRequest){
        log.info("Driver Register Start");

        driverService.registerDriver(driverRegisterRequest);

        return ResponseEntity
                .noContent()
                .build();
    }
}
