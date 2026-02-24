package com.vieri.stockcontrol.controller;

import com.vieri.stockcontrol.dto.ProductionItemResponse;
import com.vieri.stockcontrol.dto.ProductionPlanResponse;
import com.vieri.stockcontrol.service.CalculateProductionPlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductionPlanController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductionPlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculateProductionPlanService calculateService;

    @Test
    void shouldReturnProductionPlan() throws Exception {

        ProductionItemResponse item = ProductionItemResponse.builder()
                .productId(1L)
                .productName("Product A")
                .quantity(10)
                .unitPrice(new BigDecimal("100"))
                .build();

        ProductionPlanResponse response = ProductionPlanResponse.builder()
                .items(List.of(item))
                .totalRevenue(new BigDecimal("1000"))
                .build();

        when(calculateService.calculate()).thenReturn(response);

        mockMvc.perform(get("/production-plan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalRevenue").value(1000));
    }
}