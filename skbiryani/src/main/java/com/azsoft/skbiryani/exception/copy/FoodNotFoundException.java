package com.azsoft.skbiryani.exception.copy;


public class FoodNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public FoodNotFoundException(String message) {
		super(message);
	}
}