package com.bms.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

	@ExceptionHandler(exception = IdNotFoundException.class)
	public String handleException(IdNotFoundException exception)
	{
		return exception.getMessage();
	}
	
	@ExceptionHandler(exception = InValidAgeException.class)
	public String handleException(InValidAgeException exception)
	{
		return exception.getMessage();
	}
}
