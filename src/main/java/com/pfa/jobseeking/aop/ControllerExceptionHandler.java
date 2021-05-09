package com.pfa.jobseeking.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pfa.jobseeking.rest.errorresponse.ErrorResponse;
import com.pfa.jobseeking.rest.exception.NotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler
	ResponseEntity<ErrorResponse> notFoundExceptionHandler(NotFoundException e) {
		
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value() ,e.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
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
