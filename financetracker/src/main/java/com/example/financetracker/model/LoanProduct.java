package com.example.financetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class LoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;            // Hiteltermék neve
    private double maxAmount;       // Maximálisan igényelhető hitelösszeg (Ft)
    private String creditType;      // Hitel típusa (pl. Beruházási, Forgóeszköz)
    private LocalDate lastUpdated;  // Utolsó banki kondíciós lista frissítés

    // Alapértelmezett konstruktor
    public LoanProduct() {
    }

    // Konstruktor a mintaadatokhoz
    public LoanProduct(String name, double maxAmount, String creditType, LocalDate lastUpdated) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.creditType = creditType;
        this.lastUpdated = lastUpdated;
    }

    // Getterek és Setterek
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getMaxAmount() { return maxAmount; }
    public void setMaxAmount(double maxAmount) { this.maxAmount = maxAmount; }

    public String getCreditType() { return creditType; }
    public void setCreditType(String creditType) { this.creditType = creditType; }

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }
}