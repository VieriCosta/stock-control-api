package com.vieri.stockcontrol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents the complete production plan result")
public class ProductionPlanResponse {

    @Schema(description = "List of producible products")
    private List<ProductionItemResponse> items;

    @Schema(description = "Total revenue based on the suggested production", example = "10000.00")
    private BigDecimal totalRevenue;
}