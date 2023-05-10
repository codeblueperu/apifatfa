package com.fatfa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ErrorConflictException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorConflictException() {
		super();
	}

	public ErrorConflictException(String message) {
		super(message);
	}
	

}
