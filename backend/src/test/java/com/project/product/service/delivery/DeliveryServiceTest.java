package com.project.product.service.delivery;


import com.project.product.domain.delivery.Driver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class DeliveryServiceTest {

    @Mock
    private Driver driver;




}
