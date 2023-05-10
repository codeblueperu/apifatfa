package com.fatfa.exceptions;

import org.springframework.security.authentication.CredentialsExpiredException;

public class ErrorCredencialesExpired extends CredentialsExpiredException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorCredencialesExpired(String msg) {
		super(msg);
	}

}
