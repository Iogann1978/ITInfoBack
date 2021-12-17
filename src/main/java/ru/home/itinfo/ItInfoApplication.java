package ru.home.itinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ItInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItInfoApplication.class, args);
	}

}
