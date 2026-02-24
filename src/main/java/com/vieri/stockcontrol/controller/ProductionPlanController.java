package com.vieri.stockcontrol.controller;

import com.vieri.stockcontrol.dto.ProductionPlanResponse;
import com.vieri.stockcontrol.service.CalculateProductionPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Production Plan", description = "Calculate production possibilities based on stock")
public class ProductionPlanController {

    private final CalculateProductionPlanService calculateProductionPlanService;

    @Operation(summary = "Calculate production plan based on available stock")
    @GetMapping("/production-plan")
    public ProductionPlanResponse getProductionPlan() {
        return calculateProductionPlanService.calculate();
    }
}