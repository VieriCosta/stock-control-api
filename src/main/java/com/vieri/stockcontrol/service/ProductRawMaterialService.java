package com.vieri.stockcontrol.service;

import com.vieri.stockcontrol.domain.entity.Product;
import com.vieri.stockcontrol.domain.entity.ProductRawMaterial;
import com.vieri.stockcontrol.domain.entity.RawMaterial;
import com.vieri.stockcontrol.dto.ProductRawMaterialRequestDTO;
import com.vieri.stockcontrol.repository.ProductRawMaterialRepository;
import com.vieri.stockcontrol.repository.ProductRepository;
import com.vieri.stockcontrol.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductRawMaterialService {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductRawMaterialRepository productRawMaterialRepository;

    public void associate(Long productId, ProductRawMaterialRequestDTO dto) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        RawMaterial rawMaterial = rawMaterialRepository.findById(dto.getRawMaterialId())
                .orElseThrow(() -> new RuntimeException("Raw material not found"));

        ProductRawMaterial association = ProductRawMaterial.builder()
                .product(product)
                .rawMaterial(rawMaterial)
                .requiredQuantity(dto.getRequiredQuantity())
                .build();

        productRawMaterialRepository.save(association);
    }
}