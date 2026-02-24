package com.vieri.stockcontrol.service;

import com.vieri.stockcontrol.domain.entity.Product;
import com.vieri.stockcontrol.domain.entity.ProductRawMaterial;
import com.vieri.stockcontrol.domain.entity.RawMaterial;
import com.vieri.stockcontrol.dto.ProductRawMaterialRequestDTO;
import com.vieri.stockcontrol.repository.ProductRawMaterialRepository;
import com.vieri.stockcontrol.repository.ProductRepository;
import com.vieri.stockcontrol.repository.RawMaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductRawMaterialServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RawMaterialRepository rawMaterialRepository;

    @Mock
    private ProductRawMaterialRepository productRawMaterialRepository;

    @InjectMocks
    private ProductRawMaterialService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAssociateRawMaterialToProduct() {

        Product product = Product.builder().id(1L).build();
        RawMaterial rawMaterial = RawMaterial.builder().id(1L).build();

        ProductRawMaterialRequestDTO dto = ProductRawMaterialRequestDTO.builder()
                .rawMaterialId(1L)
                .requiredQuantity(10)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(rawMaterialRepository.findById(1L)).thenReturn(Optional.of(rawMaterial));

        service.associate(1L, dto);

        verify(productRawMaterialRepository).save(any(ProductRawMaterial.class));
    }
}