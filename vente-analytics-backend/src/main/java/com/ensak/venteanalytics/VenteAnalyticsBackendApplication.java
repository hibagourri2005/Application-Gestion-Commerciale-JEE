package com.ensak.venteanalytics;
//import com.ensak.venteanalytics.service.impl.CsvImportService;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class VenteAnalyticsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(VenteAnalyticsBackendApplication.class, args);
	

	/** public static void main(String[] args) {
	        ConfigurableApplicationContext context = SpringApplication.run(VenteAnalyticsBackendApplication.class, args);

	        CsvImportService importService = context.getBean(CsvImportService.class);
	        try {
	            importService.importAllData();
	            System.out.println("Importation CSV terminée avec succès !");
	        } catch (IOException | CsvValidationException e) {
	            System.err.println("Erreur lors de l'importation des CSV :");
	            e.printStackTrace();
	        }
	    }
	    **/
}}
