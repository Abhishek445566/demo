package com.restaurant.exception;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse er = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false),
				"Not Found");
		return new ResponseEntity<Object>(er, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BookTableInvalidException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(BookTableInvalidException ex, WebRequest request) {
		ExceptionResponse er = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false),
				"Not Found");
		return new ResponseEntity<ExceptionResponse>(er, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		Map<String, List<String>> errorResponse = new HashMap<>();
		errorResponse.put("", errors);
		String mapAsString = errorResponse.keySet().stream().map(key -> key + "" + errorResponse.get(key))
				.collect(Collectors.joining());
        LocalDate ld =LocalDate.now();
		List<String> list = new ArrayList<>();
		list.add(String.valueOf(ld.getYear()));
		list.add(String.valueOf(ld.getMonth()));
		list.add(String.valueOf(ld.getDayOfMonth()));

		ExceptionResponse er = new ExceptionResponse(LocalDate.now(), "validation fails " + mapAsString,
				request.getDescription(false), "Bad Request");

		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
	}



}


