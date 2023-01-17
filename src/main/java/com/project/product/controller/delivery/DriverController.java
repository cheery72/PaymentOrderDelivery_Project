package com.project.product.controller.delivery;

import com.project.product.dto.delivery.DriverRegisterRequest;
import com.project.product.dto.delivery.DriverPossibilityListResponse;
import com.project.product.service.delivery.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/driver")
@Slf4j
@RestController
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity<Object> driverRegister(
            @RequestBody @Valid DriverRegisterRequest driverRegisterRequest){

        driverService.createDriver(driverRegisterRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{city}/{gu}/{dong}/possibility")
    public ResponseEntity<List<DriverPossibilityListResponse>> possibilityDriverFind(
            @PathVariable(name = "city") String city,
            @PathVariable(name = "gu") String gu,
            @PathVariable(name = "dong") String dong){

        return ResponseEntity
                .ok(driverService.findPossibilityDriver(city,gu,dong));
    }
}
