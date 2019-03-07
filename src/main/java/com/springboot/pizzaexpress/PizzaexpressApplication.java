package com.springboot.pizzaexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.springboot.pizzaexpress")
@EnableJpaRepositories("com.springboot.pizzaexpress.dao")
@EntityScan("com.springboot.pizzaexpress.bean")
@EnableTransactionManagement
@EnableScheduling
public class PizzaexpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaexpressApplication.class, args);
	}

}
