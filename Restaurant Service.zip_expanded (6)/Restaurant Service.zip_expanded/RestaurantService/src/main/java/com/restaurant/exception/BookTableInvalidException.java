package com.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookTableInvalidException extends Exception{
	public BookTableInvalidException(String msg) {
		super(msg);
	}


}
