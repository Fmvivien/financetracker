package com.example.financetracker.service;

import com.example.financetracker.dto.LoanProductDTO;
import com.example.financetracker.model.LoanProduct;
import com.example.financetracker.repository.LoanProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanProductService {

    private final LoanProductRepository repository;

    public LoanProductService(LoanProductRepository repository) {
        this.repository = repository;
    }

    public List<LoanProductDTO> getAllProducts() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LoanProductDTO createProduct(LoanProductDTO dto) {
        LoanProduct product = new LoanProduct();
        product.setName(dto.getName());
        product.setMaxAmount(dto.getMaxAmount());
        product.setCreditType(dto.getCreditType());

        if (dto.getLastUpdated() == null) {
            product.setLastUpdated(LocalDate.now());
        } else {
            product.setLastUpdated(dto.getLastUpdated());
        }

        LoanProduct savedProduct = repository.save(product);
        return convertToDTO(savedProduct);
    }

    public List<LoanProductDTO> getHighAmountLoans(double limit) {
        return repository.findAll().stream()
                .filter(product -> product.getMaxAmount() >= limit)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private LoanProductDTO convertToDTO(LoanProduct product) {
        return new LoanProductDTO(
                product.getId(),
                product.getName(),
                product.getMaxAmount(),
                product.getCreditType(),
                product.getLastUpdated()
        );
    }
}
