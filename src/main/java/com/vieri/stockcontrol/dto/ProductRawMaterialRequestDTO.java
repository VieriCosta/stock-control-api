package com.vieri.stockcontrol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request object to associate a raw material with a product")
public class ProductRawMaterialRequestDTO {

    @Schema(description = "Raw material ID", example = "1")
    @NotNull
    private Long rawMaterialId;

    @Schema(description = "Quantity of raw material required to produce one unit of product", example = "5")
    @NotNull
    private Integer requiredQuantity;
}