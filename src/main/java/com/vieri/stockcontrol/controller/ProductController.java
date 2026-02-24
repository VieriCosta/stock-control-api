package com.vieri.stockcontrol.controller;

import com.vieri.stockcontrol.dto.ProductRequestDTO;
import com.vieri.stockcontrol.dto.ProductResponseDTO;
import com.vieri.stockcontrol.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Operations related to product management")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ProductResponseDTO create(@RequestBody ProductRequestDTO dto) {
        return productService.create(dto);
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public List<ProductResponseDTO> findAll() {
        return productService.findAll();
    }

    @Operation(summary = "Get product by ID")
    @GetMapping("/{id}")
    public ProductResponseDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @Operation(summary = "Update product")
    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable Long id,
                                     @RequestBody ProductRequestDTO dto) {
        return productService.update(id, dto);
    }

    @Operation(summary = "Delete product")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}