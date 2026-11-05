package com.example.financetracker.service;

import com.example.financetracker.dto.LoanProductDTO;
import com.example.financetracker.model.LoanProduct;
import com.example.financetracker.repository.LoanProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanProductServiceTest {

    @Mock
    private LoanProductRepository repository;

    @InjectMocks
    private LoanProductService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHighAmountLoans_FiltersCorrectly() {
        // GIVEN
        LoanProduct olcsoHitel = new LoanProduct("Olcsó Hitel", 5000000.0, "Beruházás", LocalDate.now());
        LoanProduct dragaHitel = new LoanProduct("Drága Hitel", 25000000.0, "Forgóeszköz", LocalDate.now());
        when(repository.findAll()).thenReturn(Arrays.asList(olcsoHitel, dragaHitel));

        // WHEN
        List<LoanProductDTO> eredmeny = service.getHighAmountLoans(10000000.0);

        // THEN
        assertEquals(1, eredmeny.size(), "A listában pontosan 1 elemnek kellene lennie!");
        assertEquals("Drága Hitel", eredmeny.get(0).getName(), "A megmaradt hitelnek a 'Drága Hitel'-nek kell lennie!");
    }

    @Test
    void testGetHighAmountLoans_EdgeCase_LimitEqualsMaxAmount() {
        // GIVEN
        LoanProduct pontosanTizMillios = new LoanProduct("Széchenyi Hitel", 10000000.0, "Forgóeszköz", LocalDate.now());
        when(repository.findAll()).thenReturn(Arrays.asList(pontosanTizMillios));

        // WHEN
        List<LoanProductDTO> eredmeny = service.getHighAmountLoans(10000000.0);

        // THEN
        assertEquals(1, eredmeny.size(), "Ha a limit megegyezik a hitelösszeggel, a listában 1 elemnek kell lennie!");
    }

    @Test
    void testGetHighAmountLoans_NoMatch() {
        // GIVEN
        LoanProduct hitel1 = new LoanProduct("Kis Hitel 1", 500000.0, "Lakossági", LocalDate.now());
        LoanProduct hitel2 = new LoanProduct("Kis Hitel 2", 800000.0, "Lakossági", LocalDate.now());
        when(repository.findAll()).thenReturn(Arrays.asList(hitel1, hitel2));

        // WHEN
        List<LoanProductDTO> eredmeny = service.getHighAmountLoans(5000000.0);

        // THEN
        assertEquals(0, eredmeny.size(), "Olyan határértékre, amire nincs egyező termék, üres listát kell kapnunk!");
    }

    @Test
    void testCreateProduct_WhenDateIsNull_SetsCurrentDate() {
        // GIVEN
        LoanProductDTO bejovoDTO = new LoanProductDTO(null, "Próba", 5000.0, "Folyószámlahitel", null);

        LoanProduct mentettEntity = new LoanProduct("Próba", 5000.0, "Folyószámlahitel", LocalDate.now());
        mentettEntity.setId(1L);

        when(repository.save(any(LoanProduct.class))).thenReturn(mentettEntity);

        // WHEN
        LoanProductDTO eredmenyDTO = service.createProduct(bejovoDTO);

        // THEN
        assertNotNull(eredmenyDTO.getLastUpdated());
        assertEquals(LocalDate.now(), eredmenyDTO.getLastUpdated());
    }
    @Test
    void testCreateProduct_WhenDateIsProvided_KeepsprovidedDate() {
        // GIVEN
        LocalDate egyediDatum = LocalDate.of(2026, 11, 5);
        LoanProductDTO bejovoDTO = new LoanProductDTO(null, "Egyedi Dátumos Hitel", 75000.0, "Lakossági", egyediDatum);

        LoanProduct mentettEntity = new LoanProduct("Egyedi Dátumos Hitel", 75000.0, "Lakossági", egyediDatum);
        mentettEntity.setId(5L);

        when(repository.save(any(LoanProduct.class))).thenReturn(mentettEntity);

        // WHEN
        LoanProductDTO eredmenyDTO = service.createProduct(bejovoDTO);

        // THEN
        assertNotNull(eredmenyDTO.getLastUpdated());
        assertEquals(egyediDatum, eredmenyDTO.getLastUpdated(), "A megadott dátumot változatlanul meg kellene tartani!");
        verify(repository, times(1)).save(any(LoanProduct.class));
    }
    @Test
    void testGetAllProducts_ReturnsConvertedDtoList() {
        // GIVEN
        LoanProduct hitel1 = new LoanProduct("Hitel A", 100000.0, "Típus A", LocalDate.now());
        LoanProduct hitel2 = new LoanProduct("Hitel B", 200000.0, "Típus B", LocalDate.now());

        hitel1.setId(1L);
        hitel2.setId(2L);

        when(repository.findAll()).thenReturn(Arrays.asList(hitel1, hitel2));

        // WHEN
        List<LoanProductDTO> eredmeny = service.getAllProducts();

        // THEN
        assertEquals(2, eredmeny.size(), "Két hitelterméket kellene visszakapnunk.");

        assertEquals(1L, eredmeny.get(0).getId());
        assertEquals("Hitel A", eredmeny.get(0).getName());
        assertEquals(100000.0, eredmeny.get(0).getMaxAmount());

        assertEquals(2L, eredmeny.get(1).getId());
        assertEquals("Hitel B", eredmeny.get(1).getName());

        verify(repository, times(1)).findAll();
    }
}