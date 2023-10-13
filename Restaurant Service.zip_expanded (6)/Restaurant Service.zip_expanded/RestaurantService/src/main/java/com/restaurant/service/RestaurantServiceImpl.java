package com.restaurant.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.restaurant.exception.BookTableInvalidException;
import com.restaurant.model.BookTable;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class RestaurantServiceImpl implements IRestaurantService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceImpl.class);
	private static List<BookTable> bookList=new ArrayList<BookTable>();
	
	public static List<BookTable> getBookList() {
		return bookList;
	}

	public static void setBookList(List<BookTable> bookList) {
		RestaurantServiceImpl.bookList = bookList;
	}
	
	public RestaurantServiceImpl()
	{
	
	}

	@Override
	public BookTable bookATable(BookTable obj) throws BookTableInvalidException {
		BookTable book = new BookTable();
		book.setBookingId(obj.getBookingId());
		book.setCustomerName(obj.getCustomerName());
		if(Pattern.matches("[789]{1}[0-9]{9}",obj.getMobileNumber())){
			book.setMobileNumber(obj.getMobileNumber());
		}
		else {
			LOGGER.error("Enter valid mobile number");
			throw new BookTableInvalidException("Enter valid mobile number");
		}
		book.setTotalGuestCount(obj.getTotalGuestCount());
		book.setTotalAdultVegCount(obj.getTotalAdultVegCount());
		book.setTotalAdultNonVegCount(obj.getTotalAdultNonVegCount());
		book.setTotalKidsVegCount(obj.getTotalKidsVegCount());
		book.setTotalKidsNonVegCount(obj.getTotalKidsNonVegCount());
		book.setBookingDate(obj.getBookingDate());
		book.setTotalBillAmount(null);
		bookList.add(book);
		LOGGER.info("Customer with id " + obj.getBookingId()+ " booked successfully");
		return book;
	}

	@Override
	public BookTable calculateBill(String bookingId) {
		Predicate<? super BookTable> predicate = obj -> obj.getBookingId().equals(bookingId);
		BookTable book1 = bookList.stream().filter(predicate).findAny().get();

		double costOfVegAdult = (float) (book1.getTotalAdultVegCount() * 599.0);
		double costOfNonvegAdult = (float) (book1.getTotalAdultNonVegCount() * 699.0);
		double costOfVegKid = (float) (book1.getTotalKidsVegCount() * 249.0);
		double costOfNonvegKid = (float) (book1.getTotalKidsNonVegCount() * 349.0);
		double sum = (costOfVegAdult + costOfNonvegAdult + costOfVegKid + costOfNonvegKid);

		book1.setTotalBillAmount(sum);
		LOGGER.info("Transaction completed successfully");
		return book1;
	}

	@Override
	public List<BookTable> viewBookingOnAParticularDate(LocalDate dateRequired) throws BookTableInvalidException {
		Predicate<? super BookTable> predicate = obj -> obj.getBookingDate().isEqual(dateRequired);
		List<BookTable> book = bookList.stream().filter(predicate).collect(Collectors.toList());
		if(book.contains(null)) {
			LOGGER.error("No bookings available on the given date");
			throw new BookTableInvalidException("No bookings available on the given date");
		}
		LOGGER.info("View booking on a particular date is successfully done");
		return book;
	}

	@Override
	public List<String> findStarRatedCustomer() {
		List<BookTable> collect1 = bookList.stream()
		        .collect(Collectors.groupingBy(BookTable::getMobileNumber))     
		        .entrySet()
		        .stream()
		        .max(Comparator.comparing(Map.Entry::getKey))
		        .get()
		        .getValue();
		List<String> collect = collect1.stream().map(BookTable::getMobileNumber).limit(1).collect(Collectors.toList());
		LOGGER.info("Find star rated customer is successful");
		return collect;
	}

}
