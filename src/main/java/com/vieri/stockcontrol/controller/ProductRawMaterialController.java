package com.vieri.stockcontrol.controller;

import com.vieri.stockcontrol.dto.ProductRawMaterialRequestDTO;
import com.vieri.stockcontrol.service.ProductRawMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vieri.stockcontrol.dto.ProductRawMaterialResponseDTO;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product Materials", description = "Manage association between products and raw materials")
public class ProductRawMaterialController {

    private final ProductRawMaterialService productRawMaterialService;

    @Operation(
            summary = "Associate raw material to a product",
            description = "Associates a raw material with required quantity to a product"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Association created successfully"),
            @ApiResponse(responseCode = "404", description = "Product or Raw Material not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping("/{productId}/materials")
public ResponseEntity<Void> associateMaterial(
        @PathVariable Long productId,
        @RequestBody ProductRawMaterialRequestDTO dto) {

    productRawMaterialService.associate(productId, dto);
    return ResponseEntity.noContent().build();
}

@GetMapping("/{productId}/materials")
public ResponseEntity<List<ProductRawMaterialResponseDTO>> getMaterials(
        @PathVariable Long productId) {

    return ResponseEntity.ok(
            productRawMaterialService.findByProduct(productId)
    );
}
}