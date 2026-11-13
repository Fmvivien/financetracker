package com.example.financetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanProductDTO {
    private Long id;
    private String name;
    private BigDecimal maxAmount;
    private String creditType;
    private LocalDate lastUpdated;

    public LoanProductDTO() {}


    public LoanProductDTO(Long id, String name, BigDecimal maxAmount, String creditType, LocalDate lastUpdated) {
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

    public BigDecimal getMaxAmount() { return maxAmount; }
    public void setMaxAmount(BigDecimal maxAmount) { this.maxAmount = maxAmount; }

    public String getCreditType() { return creditType; }
    public void setCreditType(String creditType) { this.creditType = creditType; }

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }
}
