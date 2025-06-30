package com.azsoft.skbiryani.exception;


public class CartNotAvailableException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public CartNotAvailableException(String message) {
		super(message);
	}
}