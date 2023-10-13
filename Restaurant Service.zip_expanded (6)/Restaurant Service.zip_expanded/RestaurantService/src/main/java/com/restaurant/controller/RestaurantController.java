package com.restaurant.controller;



import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.exception.BookTableInvalidException;
import com.restaurant.model.BookTable;
import com.restaurant.service.IRestaurantService;
import com.restaurant.validation.RestaurantValidator;
@RestController
@Validated
public class RestaurantController {
	
	@Autowired
	private IRestaurantService service;
	
	@Autowired
	private RestaurantValidator validator;
	
	@PostMapping("/book")
	public BookTable bookATable(@RequestBody @Valid BookTable bookObj) throws BookTableInvalidException {
		BookTable bookedTable = service.bookATable(bookObj);
		return bookedTable;
	}

	
	@PutMapping("calculate/{bookingId}")
	public BookTable calculateBill(@PathVariable String bookingId){
		BookTable calculateBill = service.calculateBill(bookingId);
		return calculateBill;
	}
	
	@GetMapping("viewBooking/{dateRequired}")
	public List<BookTable> viewBookingOnAParticularDate(@PathVariable String dateRequired) throws BookTableInvalidException {
		LocalDate date = LocalDate.parse(dateRequired);
		List<BookTable> book = service.viewBookingOnAParticularDate(date);
		return book;
		
	}
	

	@GetMapping("/starRated")
	public List<String> findStarRatedCustomer(){
		List<String> findStarRatedCustomer = service.findStarRatedCustomer();
		return findStarRatedCustomer;
	}
	
	

}
