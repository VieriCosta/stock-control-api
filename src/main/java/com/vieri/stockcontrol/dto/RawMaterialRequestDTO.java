package com.vieri.stockcontrol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request object for creating or updating a raw material")
public class RawMaterialRequestDTO {

    @Schema(description = "Unique raw material code", example = "RM-001")
    @NotBlank
    private String code;

    @Schema(description = "Raw material name", example = "Iron")
    @NotBlank
    private String name;

    @Schema(description = "Available stock quantity", example = "500")
    @NotNull
    private Integer stockQuantity;
}