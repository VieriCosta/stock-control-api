package com.vieri.stockcontrol.repository;

import com.vieri.stockcontrol.domain.entity.ProductRawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRawMaterialRepository extends JpaRepository<ProductRawMaterial, Long> {

    List<ProductRawMaterial> findByProductId(Long productId);

    List<ProductRawMaterial> findByRawMaterialId(Long rawMaterialId);
}