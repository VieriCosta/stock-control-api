package com.vieri.stockcontrol.config;

import com.vieri.stockcontrol.domain.entity.Product;
import com.vieri.stockcontrol.domain.entity.RawMaterial;
import com.vieri.stockcontrol.domain.entity.ProductRawMaterial;
import com.vieri.stockcontrol.repository.ProductRepository;
import com.vieri.stockcontrol.repository.RawMaterialRepository;
import com.vieri.stockcontrol.repository.ProductRawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductRawMaterialRepository productRawMaterialRepository;

    @Override
    public void run(String... args) {

        if (productRepository.count() > 0) {
            return;
        }

        RawMaterial steel = rawMaterialRepository.save(
                new RawMaterial(null, "MAT-001", "Steel", 1000)
        );

        RawMaterial plastic = rawMaterialRepository.save(
                new RawMaterial(null, "MAT-002", "Plastic", 2000)
        );

        RawMaterial aluminum = rawMaterialRepository.save(
                new RawMaterial(null, "MAT-003", "Aluminum", 800)
        );


        Product chair = productRepository.save(
                new Product(
                        null,
                        "PROD-001",
                        "Chair",
                        new BigDecimal("120.00"),
                        null
                )
        );

        Product table = productRepository.save(
                new Product(
                        null,
                        "PROD-002",
                        "Table",
                        new BigDecimal("350.00"),
                        null
                )
        );

        Product laptopStand = productRepository.save(
                new Product(
                        null,
                        "PROD-003",
                        "Laptop Stand",
                        new BigDecimal("80.00"),
                        null
                )
        );

        productRawMaterialRepository.save(
                new ProductRawMaterial(null, chair, steel, 5)
        );

        productRawMaterialRepository.save(
                new ProductRawMaterial(null, chair, plastic, 2)
        );

        productRawMaterialRepository.save(
                new ProductRawMaterial(null, table, steel, 10)
        );

        productRawMaterialRepository.save(
                new ProductRawMaterial(null, table, aluminum, 3)
        );

        productRawMaterialRepository.save(
                new ProductRawMaterial(null, laptopStand, aluminum, 2)
        );

        System.out.println(">>> Database initialized with test data.");
    }
}