package com.cognizantmfpe.pharmacyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The Class PharmacyMedicineSupplyMicroserviceApplication.
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class PharmacyMedicineSupplyMicroserviceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PharmacyMedicineSupplyMicroserviceApplication.class, args);
	}

}
