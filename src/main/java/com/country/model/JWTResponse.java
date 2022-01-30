package com.country.model;

import java.io.Serializable;

public class JWTResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String JWTToken;

	public JWTResponse(String JWTToken) {
		this.JWTToken = JWTToken;
	}

	public String getToken() {
		return this.JWTToken;
	}
}