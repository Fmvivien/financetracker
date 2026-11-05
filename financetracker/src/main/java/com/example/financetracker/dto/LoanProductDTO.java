package com.example.financetracker.dto;

import java.time.LocalDate;

public class LoanProductDTO {
    private Long id;
    private String name;
    private double maxAmount;
    private String creditType;
    private LocalDate lastUpdated;

    public LoanProductDTO() {}


    public LoanProductDTO(Long id, String name, double maxAmount, String creditType, LocalDate lastUpdated) {
        this.id = id;
        this.name = name;
        this.maxAmount = maxAmount;
        this.creditType = creditType;
        this.lastUpdated = lastUpdated;
    }

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
