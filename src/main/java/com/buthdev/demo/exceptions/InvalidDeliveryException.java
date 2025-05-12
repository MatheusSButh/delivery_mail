package com.buthdev.demo.exceptions;

public class InvalidDeliveryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidDeliveryException() {
		super("It is not possible to calculate deliveries between these addresses.");	
	}
}
