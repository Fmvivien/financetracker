package com.example.financetracker;

import com.example.financetracker.model.LoanProduct;
import com.example.financetracker.repository.LoanProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class FinancetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancetrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(LoanProductRepository repository) {
		return args -> {
			if (repository.count() == 0) {
				repository.save(new LoanProduct(
						"Széchenyi Kártya Folyószámlahitel Max+",
						new BigDecimal("100000000.00"),
						"Folyószámlahitel",
						LocalDate.now()
				));
				repository.save(new LoanProduct(
						"Garantiqa Kezességgel biztosított Forgóeszközhitel",
						new BigDecimal("250000000.00"),
						"Forgóeszközhitel",
						LocalDate.now()
				));
				repository.save(new LoanProduct(
						"MFB Beruházási és Fejlesztési Hitel",
						new BigDecimal("500000000.00"),
						"Beruházás",
						LocalDate.now()
				));
				System.out.println(">> Nyilvános banki hiteltermékek sikeresen betöltve a MySQL adatbázisba! <<");
			} else {
				System.out.println(">> Az adatbázis már tartalmaz adatokat, az inicializálás átugorva. <<");
			}
		};
	}
}