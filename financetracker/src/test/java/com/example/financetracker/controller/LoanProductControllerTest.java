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
import static org.mockito.Mockito.when;

class LoanProductControllerTest {

    @Mock
    private LoanProductRepository repository; // Szimulált adatbázis réteg

    @InjectMocks
    private LoanProductController controller; // A tesztelendő controller, amibe a Mockito beilleszti a szimulációt

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializálja a mockokat minden teszt előtt
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
        assertEquals(0, eredmeny.size(), "Ha a limit megegyezik a hitelösszeggel, az eredménynek üresnek kell lennie!");
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
}