package com.vieri.stockcontrol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vieri.stockcontrol.dto.ProductRawMaterialRequestDTO;
import com.vieri.stockcontrol.service.ProductRawMaterialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductRawMaterialController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductRawMaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRawMaterialService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldAssociateRawMaterialToProduct() throws Exception {

        ProductRawMaterialRequestDTO request = ProductRawMaterialRequestDTO.builder()
                .rawMaterialId(1L)
                .requiredQuantity(10)
                .build();

        doNothing().when(service).associate(eq(1L), eq(request));

        mockMvc.perform(post("/products/1/materials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }
}