package com.vieri.stockcontrol.service;

import com.vieri.stockcontrol.domain.entity.RawMaterial;
import com.vieri.stockcontrol.dto.RawMaterialRequestDTO;
import com.vieri.stockcontrol.dto.RawMaterialResponseDTO;
import com.vieri.stockcontrol.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RawMaterialService {

    private final RawMaterialRepository rawMaterialRepository;

    public RawMaterialResponseDTO create(RawMaterialRequestDTO dto) {

        RawMaterial rawMaterial = RawMaterial.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .stockQuantity(dto.getStockQuantity())
                .build();

        rawMaterial = rawMaterialRepository.save(rawMaterial);

        return toResponse(rawMaterial);
    }

    public List<RawMaterialResponseDTO> findAll() {
        return rawMaterialRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public RawMaterialResponseDTO findById(Long id) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raw material not found"));

        return toResponse(rawMaterial);
    }

    public RawMaterialResponseDTO update(Long id, RawMaterialRequestDTO dto) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raw material not found"));

        rawMaterial.setCode(dto.getCode());
        rawMaterial.setName(dto.getName());
        rawMaterial.setStockQuantity(dto.getStockQuantity());

        return toResponse(rawMaterialRepository.save(rawMaterial));
    }

    public void delete(Long id) {
        rawMaterialRepository.deleteById(id);
    }

    private RawMaterialResponseDTO toResponse(RawMaterial rawMaterial) {
        return RawMaterialResponseDTO.builder()
                .id(rawMaterial.getId())
                .code(rawMaterial.getCode())
                .name(rawMaterial.getName())
                .stockQuantity(rawMaterial.getStockQuantity())
                .build();
    }
}