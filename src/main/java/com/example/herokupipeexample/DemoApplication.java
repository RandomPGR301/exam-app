package com.example.herokupipeexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Clara", "Cow"));
			repository.save(new Customer("Long", "Bone"));
			repository.save(new Customer("Mickey", "Mouse"));
			repository.save(new Customer("Donald", "Duck"));
			repository.save(new Customer("Peter", "Clever"));
			repository.save(new Customer("Minnie", "Mouse"));
		};
	}


}
