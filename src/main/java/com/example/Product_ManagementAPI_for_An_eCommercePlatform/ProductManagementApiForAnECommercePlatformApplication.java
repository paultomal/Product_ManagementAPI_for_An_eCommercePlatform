package com.example.Product_ManagementAPI_for_An_eCommercePlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductManagementApiForAnECommercePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementApiForAnECommercePlatformApplication.class, args);
	}

}
