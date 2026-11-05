package com.example.financetracker.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class LoanProductDTOTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        // GIVEN
        LoanProductDTO dto = new LoanProductDTO();
        LocalDate most = LocalDate.now();

        // WHEN
        dto.setId(10L);
        dto.setName("Teszt Hitel");
        dto.setMaxAmount(1500000.0);
        dto.setCreditType("Személyi kölcsön");
        dto.setLastUpdated(most);

        // THEN
        assertEquals(10L, dto.getId());
        assertEquals("Teszt Hitel", dto.getName());
        assertEquals(1500000.0, dto.getMaxAmount());
        assertEquals("Személyi kölcsön", dto.getCreditType());
        assertEquals(most, dto.getLastUpdated());
    }

    @Test
    void testAllArgsConstructor() {
        // GIVEN
        LocalDate most = LocalDate.now();

        // WHEN
        LoanProductDTO dto = new LoanProductDTO(20L, "Lakáshitel", 50000000.0, "Jelzálog", most);

        // THEN
        assertEquals(20L, dto.getId());
        assertEquals("Lakáshitel", dto.getName());
        assertEquals(50000000.0, dto.getMaxAmount());
        assertEquals("Jelzálog", dto.getCreditType());
        assertEquals(most, dto.getLastUpdated());
    }
}