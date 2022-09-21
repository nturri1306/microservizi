package it.csa.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Patient API", version = "2.0", description = "Patient Information"))
public class PatientWriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientWriteApplication.class, args);
	}

}
