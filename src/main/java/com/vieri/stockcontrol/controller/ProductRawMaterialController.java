package com.vieri.stockcontrol.controller;

import com.vieri.stockcontrol.dto.ProductRawMaterialRequestDTO;
import com.vieri.stockcontrol.service.ProductRawMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product Materials", description = "Manage association between products and raw materials")
public class ProductRawMaterialController {

    private final ProductRawMaterialService productRawMaterialService;

    @Operation(summary = "Associate raw material to a product")
    @PostMapping("/{productId}/materials")
    public void associateMaterial(@PathVariable Long productId,
                                  @RequestBody ProductRawMaterialRequestDTO dto) {
        productRawMaterialService.associate(productId, dto);
    }
}