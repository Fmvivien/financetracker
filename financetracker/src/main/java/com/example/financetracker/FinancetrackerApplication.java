package com.example.financetracker;

import com.example.financetracker.model.LoanProduct;
import com.example.financetracker.repository.LoanProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;

@SpringBootApplication
public class FinancetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancetrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(LoanProductRepository repository) {
		return args -> {
			repository.save(new LoanProduct("Széchenyi Kártya Folyószámlahitel Max+", 100000000.0, "Folyószámlahitel", LocalDate.now()));
			repository.save(new LoanProduct("Garantiqa Kezességgel biztosított Forgóeszközhitel", 250000000.0, "Forgóeszköz", LocalDate.now()));
			repository.save(new LoanProduct("MBH Beruházási és Fejlesztési Hitel", 500000000.0, "Beruházás", LocalDate.now()));

			System.out.println(">> Nyilvános banki hiteltermékek sikeresen betöltve a H2-be! <<");
		};
	}
}