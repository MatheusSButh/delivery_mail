package com.buthdev.demo.exceptions;

public class InvalidCepException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidCepException() {
		super("The cep field must be 8 characters.");	
	}
}
