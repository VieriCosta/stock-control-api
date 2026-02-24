package com.vieri.stockcontrol.controller;

import com.vieri.stockcontrol.dto.RawMaterialRequestDTO;
import com.vieri.stockcontrol.dto.RawMaterialResponseDTO;
import com.vieri.stockcontrol.service.RawMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raw-materials")
@RequiredArgsConstructor
@Tag(name = "Raw Materials", description = "Operations related to raw material management")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    @Operation(summary = "Create a new raw material")
    @PostMapping
    public RawMaterialResponseDTO create(@RequestBody RawMaterialRequestDTO dto) {
        return rawMaterialService.create(dto);
    }

    @Operation(summary = "Get all raw materials")
    @GetMapping
    public List<RawMaterialResponseDTO> findAll() {
        return rawMaterialService.findAll();
    }

    @Operation(summary = "Get raw material by ID")
    @GetMapping("/{id}")
    public RawMaterialResponseDTO findById(@PathVariable Long id) {
        return rawMaterialService.findById(id);
    }

    @Operation(summary = "Update raw material")
    @PutMapping("/{id}")
    public RawMaterialResponseDTO update(@PathVariable Long id,
                                         @RequestBody RawMaterialRequestDTO dto) {
        return rawMaterialService.update(id, dto);
    }

    @Operation(summary = "Delete raw material")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rawMaterialService.delete(id);
    }
}