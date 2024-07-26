package com.akshay.bankSystem.payloads;

import lombok.Data;

@Data
public class JwtRequest {

	public String username;
	
	public String password;
}
