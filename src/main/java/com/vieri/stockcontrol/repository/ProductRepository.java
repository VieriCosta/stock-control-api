package com.vieri.stockcontrol.repository;

import com.vieri.stockcontrol.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}