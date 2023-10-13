package com.restaurant.model;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter
public class BookTable {
	private String bookingId;
	
	@NotNull(message = "Provide value for customer name")
	private String customerName;
	private String mobileNumber;
	
	private Integer totalGuestCount;
	@NotNull(message = "Provide value greater than or equal to zero")
	@Min(value = 0)
	private Integer totalAdultVegCount;
	@NotNull(message = "Provide value greater than or equal to zero")
	@Min(value = 0)
	private Integer totalAdultNonVegCount;
	@NotNull(message = "Provide value greater than or equal to zero")
	@Min(value = 0)
	private Integer totalKidsVegCount;
	@NotNull(message = "Provide value greater than or equal to zero")
	@Min(value = 0)
	private Integer totalKidsNonVegCount;
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;
	private Double totalBillAmount;
	
	
	public BookTable(String bookingId, String customerName, String mobileNumber, Integer totalGuestCount,
			Integer totalAdultVegCount, Integer totalAdultNonVegCount, Integer totalKidsVegCount,
			Integer totalKidsNonVegCount, LocalDate bookingDate, Double totalBillAmount) {
		super();
		this.bookingId = bookingId;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.totalGuestCount = totalGuestCount;
		this.totalAdultVegCount = totalAdultVegCount;
		this.totalAdultNonVegCount = totalAdultNonVegCount;
		this.totalKidsVegCount = totalKidsVegCount;
		this.totalKidsNonVegCount = totalKidsNonVegCount;
		this.bookingDate = bookingDate;
		this.totalBillAmount = totalBillAmount;
	}
	
	
	public BookTable() {
		
	}
	
	
	
	
	

}
