package com.buthdev.demo.exceptions;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundException(Long id) {
		super("Entity with ID" + id + "not found");	
	}
}
