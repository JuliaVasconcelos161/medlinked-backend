package com.medlinked;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Medlinked API", version = "1.0", description = "Aplicação de agendamento de consultas."))
public class MedlinkedApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedlinkedApplication.class, args);
	}

}
