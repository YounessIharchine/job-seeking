package com.pfa.jobseeking.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.response.Response;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler
	ResponseEntity<Response> notFoundExceptionHandler(NotFoundException e) {
		
		Response response = new Response(HttpStatus.NOT_FOUND.value(), e.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	ResponseEntity<Response> alreadyExistsExceptionHandler(AlreadyExistsException e) {
		
		Response response = new Response(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
	
//	@ExceptionHandler
//	ResponseEntity<ErrorResponse> genericExceptionHandler(Exception e) {
//		
//		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
//		
//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		
//	}
}
