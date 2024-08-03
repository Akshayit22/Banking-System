package com.akshay.bankSystem.exceptions;

import com.akshay.bankSystem.entities.Transaction;

public class TransactionError {

	String message;
	Transaction details;

	public TransactionError() {

	}

	public TransactionError(String message) {
		super();
		this.message = message;
	}

	public TransactionError(String message, Transaction details) {
		super();
		this.message = message;
		this.details = details;
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

}
