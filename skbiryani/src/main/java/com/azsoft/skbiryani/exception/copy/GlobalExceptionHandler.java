package com.azsoft.skbiryani.exception.copy;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.azsoft.skbiryani.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(FoodNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleFoodNotFoundException(FoodNotFoundException ex, WebRequest webRequest){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setPath(webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
