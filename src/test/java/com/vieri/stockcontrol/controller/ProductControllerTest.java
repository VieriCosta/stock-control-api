package com.vieri.stockcontrol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vieri.stockcontrol.dto.ProductRequestDTO;
import com.vieri.stockcontrol.dto.ProductResponseDTO;
import com.vieri.stockcontrol.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateProduct() throws Exception {

        ProductRequestDTO request = ProductRequestDTO.builder()
                .code("PRD-1")
                .name("Product A")
                .price(new BigDecimal("100"))
                .build();

        ProductResponseDTO response = ProductResponseDTO.builder()
                .id(1L)
                .code("PRD-1")
                .name("Product A")
                .price(new BigDecimal("100"))
                .build();

        when(productService.create(any())).thenReturn(response);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Product A"));
    }

    @Test
    void shouldReturnAllProducts() throws Exception {

        ProductResponseDTO product = ProductResponseDTO.builder()
                .id(1L)
                .code("PRD-1")
                .name("Product A")
                .price(new BigDecimal("100"))
                .build();

        when(productService.findAll()).thenReturn(List.of(product));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void shouldReturnProductById() throws Exception {

        ProductResponseDTO product = ProductResponseDTO.builder()
                .id(1L)
                .code("PRD-1")
                .name("Product A")
                .price(new BigDecimal("100"))
                .build();

        when(productService.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldUpdateProduct() throws Exception {

        ProductRequestDTO request = ProductRequestDTO.builder()
                .code("PRD-1")
                .name("Updated Product")
                .price(new BigDecimal("150"))
                .build();

        ProductResponseDTO response = ProductResponseDTO.builder()
                .id(1L)
                .code("PRD-1")
                .name("Updated Product")
                .price(new BigDecimal("150"))
                .build();

        when(productService.update(eq(1L), any())).thenReturn(response);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"));
    }

    @Test
    void shouldDeleteProduct() throws Exception {

        doNothing().when(productService).delete(1L);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());
    }
}