package com.akshay.bankSystem.payloads;

import lombok.Data;

@Data
public class JwtRequest {

	public String userName;
	
	public String password;

	public JwtRequest() {
		super();
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
