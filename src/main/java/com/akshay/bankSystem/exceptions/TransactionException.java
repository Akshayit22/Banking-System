package com.akshay.bankSystem.exceptions;

import com.akshay.bankSystem.entities.Transaction;

public class TransactionException extends RuntimeException{
	
	String message;
	Transaction details;
	int accountNumber;
	
	public TransactionException(String message, Transaction details,int accountNumber) {
		super();
		this.message = message;
		this.details = details;
		this.accountNumber = accountNumber;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Transaction getDetails() {
		return details;
	}

	public void setDetails(Transaction details) {
		this.details = details;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
