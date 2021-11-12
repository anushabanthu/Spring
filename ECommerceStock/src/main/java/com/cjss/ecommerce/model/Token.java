package com.cjss.ecommerce.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Token {
	private String email;
	private LocalDateTime tokenExpiryDateTime;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getTokenExpiryDateTime() {
		return tokenExpiryDateTime;
	}

	public void setTokenExpiryDateTime(LocalDateTime tokenExpiryDateTime) {
		this.tokenExpiryDateTime = tokenExpiryDateTime;
	}

	public String createToken() {
		return UUID.randomUUID().toString();
	}
}

