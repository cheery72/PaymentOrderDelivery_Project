package com.project.product.controller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.product.domain.order.PayType;
import com.project.product.dto.order.OrderCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    private final String BASE_URL = "/order";

    @BeforeEach()
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("주문 생성")
    public void orderCreateTest() throws Exception {
        String body = objectMapper.writeValueAsString(
                OrderCreateRequest.builder()
                        .productId(List.of(1L,2L))
                        .purchaser(1L)
                        .totalPrice(15000)
                        .usePoint(12000)
                        .purchaserMemo("안녕")
                        .payType(PayType.ALL.name())
                        .cardId(1L)
                        .couponId(1L)
                        .build()
        );

        mockMvc.perform(post(BASE_URL + "/create")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}