package com.vieri.stockcontrol.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRawMaterialResponseDTO {

    private Long id;
    private Long rawMaterialId;
    private String rawMaterialName;
    private Integer requiredQuantity;
    private Integer stockQuantity;
}