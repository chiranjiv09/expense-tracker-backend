package com.chiranjiv.expense.security;

import org.springframework.stereotype.Component;

/**
 * @author chiranjiv.kushwah
 * 
 */
@Component
public class JwtAuthResponse {
	
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}

}
