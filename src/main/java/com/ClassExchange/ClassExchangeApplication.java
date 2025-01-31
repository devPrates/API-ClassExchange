package com.ClassExchange;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Documentação ClassExchange", version = "1.0", description = "API para troca e substituição de aulas"))
public class ClassExchangeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClassExchangeApplication.class, args);

	}



}
