package com.vieri.stockcontrol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents one product in the calculated production plan")
public class ProductionItemResponse {

    @Schema(description = "Product ID", example = "1")
    private Long productId;

    @Schema(description = "Product name", example = "Steel Beam")
    private String productName;

    @Schema(description = "Number of units that can be produced", example = "10")
    private Integer quantity;

    @Schema(description = "Unit price of the product", example = "150.50")
    private BigDecimal unitPrice;

    @Schema(description = "Total revenue for this product", example = "1505.00")
    private BigDecimal totalValue;
}