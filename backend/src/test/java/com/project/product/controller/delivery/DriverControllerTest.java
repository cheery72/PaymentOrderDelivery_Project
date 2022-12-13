package com.project.product.controller.delivery;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.product.domain.delivery.DriverStatus;
import com.project.product.dto.delivery.DriverRegisterRequest;
import com.project.product.dto.delivery.DriverPossibilityListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class DriverControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private DriverPossibilityListResponse driverPossibilityListResponse;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    private final String BASE_URL = "/driver";

    @BeforeEach()
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

    @Test
    public void driverRegister() throws Exception{
        String body = objectMapper.writeValueAsString(
                DriverRegisterRequest.builder()
                        .name("김경민")
                        .phone("010-0000-0000")
                        .addressCity("시")
                        .addressGu("구")
                        .addressDong("동")
                        .build()
        );

        mockMvc.perform(post(BASE_URL+"/register")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void possibilityDriverFind() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/시" + "/구" + "/동" + "/possibility")
                ).andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<DriverPossibilityListResponse> driverPossibilityListResponses
                = List.of(objectMapper.readValue(contentAsString, DriverPossibilityListResponse[].class));

        for(DriverPossibilityListResponse driverPossibilityListResponse : driverPossibilityListResponses){
            assertThat(driverPossibilityListResponse.getStatus()).isNotIn(String.valueOf(DriverStatus.VACATION));
        }

    }


}
