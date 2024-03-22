package com.msservices.analisecredito;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.msservices.analisecredito.service.AnaliseCreditoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
public class AnalisecreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalisecreditoApplication.class, args);
	}

}
