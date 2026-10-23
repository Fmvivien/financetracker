package com.example.financetracker.controller;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class LoanProductControllerTest {

    @Mock
    private LoanProductRepository repository;

    @InjectMocks
    private LoanProductController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHighAmountLoans_FiltersCorrectly() {
        //GIVEN
        LoanProduct olcsoHitel = new LoanProduct("Olcsó Hitel", 5000000.0, "Beruházás", LocalDate.now());
        LoanProduct dragaHitel = new LoanProduct("Drága Hitel", 25000000.0, "Forgóeszköz", LocalDate.now());
        when(repository.findAll()).thenReturn(Arrays.asList(olcsoHitel, dragaHitel));

        // WHEN
        List<LoanProduct> eredmeny = controller.getHighAmountLoans(10000000.0);

        //THEN
        assertEquals(1, eredmeny.size(), "A listában pontosan 1 elemnek kellene lennie!");
        assertEquals("Drága Hitel", eredmeny.get(0).getName(), "A megmaradt hitelnek a 'Drága Hitel'-nek kell lennie!");
    }
    @Test
    void testGetHighAmountLoans_EdgeCase_LimitEqualsMaxAmount() {
        // GIVEN
        LoanProduct pontosanTizMillios = new LoanProduct("Széchenyi Hitel", 10000000.0, "Forgóeszköz", LocalDate.now());

        when(repository.findAll()).thenReturn(Arrays.asList(pontosanTizMillios));

        // WHEN
        List<LoanProduct> eredmeny = controller.getHighAmountLoans(10000000.0);

        // THEN
        assertEquals(1, eredmeny.size(), "Ha a limit megegyezik a hitelösszeggel, a listában 1 elemnek kell lennie!");
    }
    @Test
    void testCreateProduct_WhenDateIsNull_SetsCurrentDate() {
        //GIVEN
        LoanProduct tesztTermek = new LoanProduct("Próba", 5000.0, "Folyószámlahitel", null);
        when(repository.save(tesztTermek)).thenReturn(tesztTermek);

        //WHEN
        LoanProduct mentettTermek = controller.createProduct(tesztTermek);

        //THEN
        assertEquals(LocalDate.now(), mentettTermek.getLastUpdated());
    }
    @Test
    void testGetHighAmountLoans_NoMatch() {
        // GIVEN
        LoanProduct hitel1 = new LoanProduct("Kis Hitel 1", 500000.0, "Lakossági", LocalDate.now());
        LoanProduct hitel2 = new LoanProduct("Kis Hitel 2", 800000.0, "Lakossági", LocalDate.now());
        when(repository.findAll()).thenReturn(Arrays.asList(hitel1, hitel2));

        // WHEN
        List<LoanProduct> eredmeny = controller.getHighAmountLoans(5000000.0);

        // THEN
        assertEquals(0, eredmeny.size(), "Olyan határértékre, amire nincs egyező termék, üres listát kell kapnunk!");
    }

    @Test
    void testCreateProduct_Success() {
        // GIVEN
        LoanProduct ujHitel = new LoanProduct("Új Corporate Hitel", 40000000.0, "Beruházási", LocalDate.now());
        when(repository.save(ujHitel)).thenReturn(ujHitel);

        // WHEN
        LoanProduct mentett = controller.createProduct(ujHitel);

        // THEN
        assertNotNull(mentett, "A mentett hiteltermék nem lehet null!");
        assertEquals("Új Corporate Hitel", mentett.getName());
        assertEquals(40000000.0, mentett.getMaxAmount());
    }
    @Test
    void testLoanProductGettersAndSetters() {
        // GIVEN
        LoanProduct product = new LoanProduct();

        // WHEN
        product.setId(99L);
        product.setName("Teszt Termék");
        product.setMaxAmount(150000.0);
        product.setCreditType("Személyi kölcsön");
        LocalDate most = LocalDate.now();
        product.setLastUpdated(most);

        // THEN
        assertEquals(99L, product.getId());
        assertEquals("Teszt Termék", product.getName());
        assertEquals(150000.0, product.getMaxAmount());
        assertEquals("Személyi kölcsön", product.getCreditType());
        assertEquals(most, product.getLastUpdated());
    }
}