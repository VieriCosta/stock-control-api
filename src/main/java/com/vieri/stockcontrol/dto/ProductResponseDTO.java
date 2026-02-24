package com.vieri.stockcontrol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response object representing a product")
public class ProductResponseDTO {

    @Schema(description = "Product ID", example = "1")
    private Long id;

    @Schema(description = "Unique product code", example = "PRD-001")
    private String code;

    @Schema(description = "Product name", example = "Steel Beam")
    private String name;

    @Schema(description = "Product price", example = "150.50")
    private BigDecimal price;
}