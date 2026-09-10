package com.example.financetracker.controller;

import com.example.financetracker.model.LoanProduct;
import com.example.financetracker.repository.LoanProductRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*")
public class LoanProductController {

    private final LoanProductRepository repository;

    public LoanProductController(LoanProductRepository repository) {
        this.repository = repository;
    }

    // Összes banki hiteltermék lekérése
    @GetMapping
    public List<LoanProduct> getAllProducts() {
        return repository.findAll();
    }

    // Új hiteltermék adminisztrálása/mentése
    @PostMapping
    public LoanProduct createProduct(@RequestBody LoanProduct newProduct) {
        if (newProduct.getLastUpdated() == null) {
            newProduct.setLastUpdated(java.time.LocalDate.now());
        }
        return repository.save(newProduct);
    }

    // Szűrés a Stream API segítségével: Csak a limit feletti maximális hitelösszegű termékek
    @GetMapping("/szures")
    public List<LoanProduct> getHighAmountLoans(@RequestParam double limit) {
        return repository.findAll().stream()
                .filter(product -> product.getMaxAmount() >= limit)
                .collect(Collectors.toList());
    }
}