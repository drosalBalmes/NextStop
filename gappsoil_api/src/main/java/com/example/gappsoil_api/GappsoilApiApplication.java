package com.example.gappsoil_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GappsoilApiApplication {

	public static void main(String[] args) {


		SpringApplication.run(GappsoilApiApplication.class, args);

		DescargadorDiario dc = new DescargadorDiario();
		dc.run();

	}

}
