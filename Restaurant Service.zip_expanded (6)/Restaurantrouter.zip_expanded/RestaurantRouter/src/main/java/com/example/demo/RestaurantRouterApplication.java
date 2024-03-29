package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class RestaurantRouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantRouterApplication.class, args);
	}

}