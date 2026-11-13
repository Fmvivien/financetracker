package com.example.financetracker.controller;

import com.example.financetracker.dto.LoanProductDTO;
import com.example.financetracker.service.LoanProductService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*")
public class LoanProductController {

    private final LoanProductService service;

    public LoanProductController(LoanProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<LoanProductDTO> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    public LoanProductDTO createProduct(@RequestBody LoanProductDTO newProductDTO) {
        return service.createProduct(newProductDTO);
    }

    @GetMapping("/szures")
    public List<LoanProductDTO> getHighAmountLoans(@RequestParam BigDecimal limit) {
        return service.getHighAmountLoans(limit);
    }
}