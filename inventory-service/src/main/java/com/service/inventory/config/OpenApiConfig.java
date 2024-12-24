package com.service.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI inventoryServiceAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Inventory Service API Documentation")
						.description("This is the REST API doc for Inventory Service")
						.version("v0.0.1"));
	}

}
