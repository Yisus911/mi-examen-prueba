package com.examen.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExamenPruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenPruebaApplication.class, args);
	}

}
