package com.vieri.stockcontrol.service;

import com.vieri.stockcontrol.domain.entity.Product;
import com.vieri.stockcontrol.domain.entity.ProductRawMaterial;
import com.vieri.stockcontrol.domain.entity.RawMaterial;
import com.vieri.stockcontrol.dto.ProductionItemResponse;
import com.vieri.stockcontrol.dto.ProductionPlanResponse;
import com.vieri.stockcontrol.repository.ProductRepository;
import com.vieri.stockcontrol.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculateProductionPlanService {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductionPlanResponse calculate() {

        //Buscar todos produtos ordenados por preço DESC e ID ASC
        List<Product> products = productRepository.findAll()
                .stream()
                .sorted(Comparator
                        .comparing(Product::getPrice).reversed()
                        .thenComparing(Product::getId))
                .collect(Collectors.toList());

        //Clonar estoque atual (estoque virtual)
        Map<Long, Integer> virtualStock = rawMaterialRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        RawMaterial::getId,
                        RawMaterial::getStockQuantity
                ));

        List<ProductionItemResponse> productionItems = new ArrayList<>();
        BigDecimal totalRevenue = BigDecimal.ZERO;

        //Iterar sobre produtos
        for (Product product : products) {

            if (product.getMaterials() == null || product.getMaterials().isEmpty()) {
                continue;
            }

            // Calcular quantidade máxima possível
            int maxUnits = Integer.MAX_VALUE;

            for (ProductRawMaterial prm : product.getMaterials()) {

                Long rawMaterialId = prm.getRawMaterial().getId();
                Integer availableStock = virtualStock.getOrDefault(rawMaterialId, 0);
                Integer requiredQuantity = prm.getRequiredQuantity();

                if (requiredQuantity == 0) {
                    continue;
                }

                int possibleUnits = availableStock / requiredQuantity;
                maxUnits = Math.min(maxUnits, possibleUnits);
            }

            // Se não puder produzir nada, pula
            if (maxUnits <= 0) {
                continue;
            }

            //Atualizar estoque virtual
            for (ProductRawMaterial prm : product.getMaterials()) {
                Long rawMaterialId = prm.getRawMaterial().getId();
                Integer requiredQuantity = prm.getRequiredQuantity();

                int currentStock = virtualStock.get(rawMaterialId);
                virtualStock.put(rawMaterialId,
                        currentStock - (requiredQuantity * maxUnits));
            }

            // 5️⃣ Calcular valor total do produto
            BigDecimal itemTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(maxUnits));

            totalRevenue = totalRevenue.add(itemTotal);

            //Adicionar ao plano
            productionItems.add(
                    ProductionItemResponse.builder()
                            .productId(product.getId())
                            .productName(product.getName())
                            .quantity(maxUnits)
                            .unitPrice(product.getPrice())
                            .totalValue(itemTotal)
                            .build()
            );
        }

        return ProductionPlanResponse.builder()
                .items(productionItems)
                .totalRevenue(totalRevenue)
                .build();
    }
}