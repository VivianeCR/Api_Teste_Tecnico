package br.gov.pa.sefa.fadesp.payment.desafio_tecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DesafioTecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTecnicoApplication.class, args);
	}

}
