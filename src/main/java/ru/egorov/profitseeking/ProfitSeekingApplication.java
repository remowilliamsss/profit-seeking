package ru.egorov.profitseeking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProfitSeekingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfitSeekingApplication.class, args);
	}

}
