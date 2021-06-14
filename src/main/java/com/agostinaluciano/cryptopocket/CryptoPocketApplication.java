package com.agostinaluciano.cryptopocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CryptoPocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoPocketApplication.class, args);
	}

}
