package com.learning.springboot.checklistapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChecklistApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChecklistApiApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenApi(){

		return new OpenAPI()
				.info(new Info()
						.title("Checklist API for Udemy Curse")
						.description("Sample API created for learning purposes")
						.contact(new Contact()
								.name("Carlos Lazarin")
								.email("carlos.joia.mail@gmail.com")
						)
						.version("V1")
						.termsOfService("http://mywebsite.com.br")
						.license(
								new License()
								.name("Apache 2.0")
						)
				);

	}

}
