package com.akshay.bankSystem.payloads.request;

import lombok.Data;

@Data
public class JwtRequest {

	public String userName;
	
	public String password;

	public JwtRequest() {
		super();
	}


	public JwtRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	
	
	
}
