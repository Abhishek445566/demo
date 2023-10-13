package com.restaurant.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.restaurant.controller"})
public class RestaurantGreetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantGreetingApplication.class, args);
	}

}
