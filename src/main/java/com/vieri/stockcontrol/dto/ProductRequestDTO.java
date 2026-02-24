package com.vieri.stockcontrol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request object for creating or updating a product")
public class ProductRequestDTO {

    @Schema(description = "Unique product code", example = "PRD-001")
    @NotBlank
    private String code;

    @Schema(description = "Product name", example = "Steel Beam")
    @NotBlank
    private String name;

    @Schema(description = "Product price", example = "150.50")
    @NotNull
    private BigDecimal price;
}