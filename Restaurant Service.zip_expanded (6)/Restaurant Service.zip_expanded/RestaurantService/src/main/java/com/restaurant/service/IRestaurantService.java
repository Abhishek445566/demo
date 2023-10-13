package com.restaurant.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurant.exception.BookTableInvalidException;
import com.restaurant.model.BookTable;
@Service
public interface IRestaurantService {
	public BookTable bookATable(BookTable obj) throws BookTableInvalidException;
	public BookTable calculateBill(String bookingId);
	public List<BookTable> viewBookingOnAParticularDate(LocalDate dateRequired) throws BookTableInvalidException;
	public List<String> findStarRatedCustomer();
}
