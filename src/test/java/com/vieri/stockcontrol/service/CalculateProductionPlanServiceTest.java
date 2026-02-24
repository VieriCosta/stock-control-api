package com.vieri.stockcontrol.service;

import com.vieri.stockcontrol.domain.entity.Product;
import com.vieri.stockcontrol.domain.entity.ProductRawMaterial;
import com.vieri.stockcontrol.domain.entity.RawMaterial;
import com.vieri.stockcontrol.dto.ProductionPlanResponse;
import com.vieri.stockcontrol.repository.ProductRepository;
import com.vieri.stockcontrol.repository.RawMaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CalculateProductionPlanServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RawMaterialRepository rawMaterialRepository;

    @InjectMocks
    private CalculateProductionPlanService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // TESTE 1 – Prioriza produto de maior valor
    @Test
    void shouldCalculateProductionPlanPrioritizingHigherPrice() {

        RawMaterial iron = RawMaterial.builder()
                .id(1L)
                .stockQuantity(100)
                .build();

        Product productA = Product.builder()
                .id(1L)
                .price(new BigDecimal("200"))
                .build();

        ProductRawMaterial prmA = ProductRawMaterial.builder()
                .product(productA)
                .rawMaterial(iron)
                .requiredQuantity(10)
                .build();

        productA.setMaterials(List.of(prmA));

        Product productB = Product.builder()
                .id(2L)
                .price(new BigDecimal("100"))
                .build();

        ProductRawMaterial prmB = ProductRawMaterial.builder()
                .product(productB)
                .rawMaterial(iron)
                .requiredQuantity(10)
                .build();

        productB.setMaterials(List.of(prmB));

        when(productRepository.findAll()).thenReturn(List.of(productA, productB));
        when(rawMaterialRepository.findAll()).thenReturn(List.of(iron));

        ProductionPlanResponse response = service.calculate();

        assertThat(response.getItems()).hasSize(1);
        assertThat(response.getItems().get(0).getQuantity()).isEqualTo(10);
        assertThat(response.getTotalRevenue()).isEqualByComparingTo("2000");
    }

    // TESTE 2 – Estoque insuficiente
    @Test
    void shouldReturnEmptyWhenStockIsInsufficient() {

        RawMaterial iron = RawMaterial.builder()
                .id(1L)
                .stockQuantity(5)
                .build();

        Product product = Product.builder()
                .id(1L)
                .price(new BigDecimal("100"))
                .build();

        ProductRawMaterial prm = ProductRawMaterial.builder()
                .product(product)
                .rawMaterial(iron)
                .requiredQuantity(10)
                .build();

        product.setMaterials(List.of(prm));

        when(productRepository.findAll()).thenReturn(List.of(product));
        when(rawMaterialRepository.findAll()).thenReturn(List.of(iron));

        ProductionPlanResponse response = service.calculate();

        assertThat(response.getItems()).isEmpty();
        assertThat(response.getTotalRevenue()).isEqualByComparingTo("0");
    }

    // TESTE 3 – Produto com dois materiais (mínimo entre insumos)
    @Test
    void shouldUseMinimumStockBetweenMaterials() {

        RawMaterial iron = RawMaterial.builder()
                .id(1L)
                .stockQuantity(100)
                .build();

        RawMaterial wood = RawMaterial.builder()
                .id(2L)
                .stockQuantity(20)
                .build();

        Product product = Product.builder()
                .id(1L)
                .price(new BigDecimal("50"))
                .build();

        ProductRawMaterial prm1 = ProductRawMaterial.builder()
                .product(product)
                .rawMaterial(iron)
                .requiredQuantity(10)
                .build();

        ProductRawMaterial prm2 = ProductRawMaterial.builder()
                .product(product)
                .rawMaterial(wood)
                .requiredQuantity(5)
                .build();

        product.setMaterials(List.of(prm1, prm2));

        when(productRepository.findAll()).thenReturn(List.of(product));
        when(rawMaterialRepository.findAll()).thenReturn(List.of(iron, wood));

        ProductionPlanResponse response = service.calculate();

        // Iron permite 10 unidades (100/10)
        // Wood permite 4 unidades (20/5)
        // Resultado deve ser 4
        assertThat(response.getItems()).hasSize(1);
        assertThat(response.getItems().get(0).getQuantity()).isEqualTo(4);
    }

    // TESTE 4 – Produto sem materiais
    @Test
    void shouldIgnoreProductWithoutMaterials() {

        Product product = Product.builder()
                .id(1L)
                .price(new BigDecimal("100"))
                .build();

        product.setMaterials(List.of());

        when(productRepository.findAll()).thenReturn(List.of(product));
        when(rawMaterialRepository.findAll()).thenReturn(List.of());

        ProductionPlanResponse response = service.calculate();

        assertThat(response.getItems()).isEmpty();
    }
}