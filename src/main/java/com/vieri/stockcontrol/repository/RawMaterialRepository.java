package com.vieri.stockcontrol.repository;

import com.vieri.stockcontrol.domain.entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}