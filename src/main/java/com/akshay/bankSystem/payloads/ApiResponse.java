package com.akshay.bankSystem.payloads;

import lombok.Data;

@Data
public class ApiResponse {

	private String messageString;
	private boolean success;
	
	
	public ApiResponse(String messageString, boolean success) {
		super();
		this.messageString = messageString;
		this.success = success;
	}
	public ApiResponse() {
		super();
	}
	public String getMessageString() {
		return messageString;
	}
	public void setMessageString(String messageString) {
		this.messageString = messageString;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	
}
