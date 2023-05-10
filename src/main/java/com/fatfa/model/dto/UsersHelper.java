package com.fatfa.model.dto;

import org.springframework.security.core.userdetails.User;


public class UsersHelper extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsersHelper(UsersPojo user) {
		super(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
				user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getListOfgrantedAuthorities());
	}

}
