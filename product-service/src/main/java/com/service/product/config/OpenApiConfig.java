package com.service.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI productServiceAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Product Service API Documentation")
						.description("This is the REST API doc for Product Service")
						.version("v0.0.1"));
	}

}