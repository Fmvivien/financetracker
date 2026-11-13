package com.example.financetracker.controller;

import com.example.financetracker.dto.LoanProductDTO;
import com.example.financetracker.service.LoanProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanProductControllerTest {

    @Mock
    private LoanProductService service;

    @InjectMocks
    private LoanProductController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // GIVEN
        LoanProductDTO dto1 = new LoanProductDTO(1L, "Hitel 1", new BigDecimal(500000.00), "Lakossági", LocalDate.now());
        LoanProductDTO dto2 = new LoanProductDTO(2L, "Hitel 2", new BigDecimal(800000.00), "Lakossági", LocalDate.now());
        when(service.getAllProducts()).thenReturn(Arrays.asList(dto1, dto2));

        // WHEN
        List<LoanProductDTO> eredmeny = controller.getAllProducts();

        // THEN
        assertEquals(2, eredmeny.size());
        verify(service, times(1)).getAllProducts();
    }

    @Test
    void testGetHighAmountLoans() {
        // GIVEN
        BigDecimal limit = BigDecimal.valueOf(1000000.0);
        LoanProductDTO dragaHitel = new LoanProductDTO(2L, "Drága Hitel", new BigDecimal(2500000.00), "Forgóeszköz", LocalDate.now());
        when(service.getHighAmountLoans(limit)).thenReturn(Arrays.asList(dragaHitel));

        // WHEN
        List<LoanProductDTO> eredmeny = controller.getHighAmountLoans(limit);

        // THEN
        assertEquals(1, eredmeny.size());
        assertEquals("Drága Hitel", eredmeny.get(0).getName());
        verify(service, times(1)).getHighAmountLoans(limit);
    }

    @Test
    void testCreateProduct_Success() {
        // GIVEN
        LoanProductDTO bejovoDTO = new LoanProductDTO(null, "Új Corporate Hitel", new BigDecimal("40000000.00"), "Beruházási", LocalDate.now());
        LoanProductDTO mentettDTO = new LoanProductDTO(1L, "Új Corporate Hitel", new BigDecimal("40000000.00"), "Beruházási", LocalDate.now());
        when(service.createProduct(any(LoanProductDTO.class))).thenReturn(mentettDTO);

        // WHEN
        LoanProductDTO mentett = controller.createProduct(bejovoDTO);

        // THEN
        assertNotNull(mentett);
        assertEquals(1L, mentett.getId());
        assertEquals("Új Corporate Hitel", mentett.getName());
        assertEquals(new BigDecimal("40000000.00"), mentett.getMaxAmount());
    }
}