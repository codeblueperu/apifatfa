package com.fatfa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ErrorNotFoundException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public ErrorNotFoundException() {
		super();
	}

	public ErrorNotFoundException(String message) {
		super(message);
	}
}
