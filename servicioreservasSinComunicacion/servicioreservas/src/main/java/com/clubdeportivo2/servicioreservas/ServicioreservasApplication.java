package com.clubdeportivo2.servicioreservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ServicioreservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioreservasApplication.class, args);
	}

}
