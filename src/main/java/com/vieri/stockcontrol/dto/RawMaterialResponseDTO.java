package com.vieri.stockcontrol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response object representing a raw material")
public class RawMaterialResponseDTO {

    @Schema(description = "Raw material ID", example = "1")
    private Long id;

    @Schema(description = "Unique raw material code", example = "RM-001")
    private String code;

    @Schema(description = "Raw material name", example = "Iron")
    private String name;

    @Schema(description = "Available stock quantity", example = "500")
    private Integer stockQuantity;
}