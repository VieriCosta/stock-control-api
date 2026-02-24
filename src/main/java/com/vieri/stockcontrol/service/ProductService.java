package com.vieri.stockcontrol.service;

import com.vieri.stockcontrol.domain.entity.Product;
import com.vieri.stockcontrol.dto.ProductRequestDTO;
import com.vieri.stockcontrol.dto.ProductResponseDTO;
import com.vieri.stockcontrol.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDTO create(ProductRequestDTO dto) {

        Product product = Product.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .price(dto.getPrice())
                .build();

        product = productRepository.save(product);

        return toResponse(product);
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return toResponse(product);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        return toResponse(productRepository.save(product));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private ProductResponseDTO toResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}