package com.vieri.stockcontrol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vieri.stockcontrol.dto.RawMaterialRequestDTO;
import com.vieri.stockcontrol.dto.RawMaterialResponseDTO;
import com.vieri.stockcontrol.service.RawMaterialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RawMaterialController.class)
@AutoConfigureMockMvc(addFilters = false)
class RawMaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RawMaterialService rawMaterialService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateRawMaterial() throws Exception {

        RawMaterialRequestDTO request = RawMaterialRequestDTO.builder()
                .code("RM-1")
                .name("Iron")
                .stockQuantity(100)
                .build();

        RawMaterialResponseDTO response = RawMaterialResponseDTO.builder()
                .id(1L)
                .code("RM-1")
                .name("Iron")
                .stockQuantity(100)
                .build();

        when(rawMaterialService.create(any())).thenReturn(response);

        mockMvc.perform(post("/raw-materials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Iron"));
    }

    @Test
    void shouldReturnAllRawMaterials() throws Exception {

        RawMaterialResponseDTO material = RawMaterialResponseDTO.builder()
                .id(1L)
                .code("RM-1")
                .name("Iron")
                .stockQuantity(100)
                .build();

        when(rawMaterialService.findAll()).thenReturn(List.of(material));

        mockMvc.perform(get("/raw-materials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void shouldReturnRawMaterialById() throws Exception {

        RawMaterialResponseDTO material = RawMaterialResponseDTO.builder()
                .id(1L)
                .code("RM-1")
                .name("Iron")
                .stockQuantity(100)
                .build();

        when(rawMaterialService.findById(1L)).thenReturn(material);

        mockMvc.perform(get("/raw-materials/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldUpdateRawMaterial() throws Exception {

        RawMaterialRequestDTO request = RawMaterialRequestDTO.builder()
                .code("RM-1")
                .name("Steel")
                .stockQuantity(200)
                .build();

        RawMaterialResponseDTO response = RawMaterialResponseDTO.builder()
                .id(1L)
                .code("RM-1")
                .name("Steel")
                .stockQuantity(200)
                .build();

        when(rawMaterialService.update(eq(1L), any())).thenReturn(response);

        mockMvc.perform(put("/raw-materials/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Steel"));
    }

    @Test
    void shouldDeleteRawMaterial() throws Exception {

        doNothing().when(rawMaterialService).delete(1L);

        mockMvc.perform(delete("/raw-materials/1"))
                .andExpect(status().isNoContent());
    }
}