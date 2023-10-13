package com.restaurant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	
	@GetMapping(value="/welcome")
	public @ResponseBody String greeting( ) {
		
		return "Welcome to our restaurant,We offer wide varieties of delicious food items.Spread over an area of 2,600 sq ft, restaurant has a seating capacity of 110.";
		
	}
}
