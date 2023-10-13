package com.restaurant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
public class ApplicationController {

	
	
	@GetMapping("/greet")
	@CircuitBreaker(name="default", fallbackMethod = "fallback")
	public String retreiveInfo( ) {
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8091/welcome",String.class);
	    return forEntity.getBody();
		
	}
	
	public String fallback(Exception e) {
		return "Sorry Service is unavailable";
	}
	
}
