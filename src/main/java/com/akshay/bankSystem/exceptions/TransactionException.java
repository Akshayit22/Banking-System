package com.akshay.bankSystem.exceptions;

import com.akshay.bankSystem.entities.Transaction;

public class TransactionException extends RuntimeException {

	String message;
	Transaction details;
	int fieldValue;

	public TransactionException() {
		super();
	}

	public TransactionException(String message, Transaction details, int fieldValue) {
		super();
		this.message = message;
		this.details = details;
		this.fieldValue = fieldValue;
	}

	public TransactionException(String message, int fieldValue) {
		super();
		this.message = message;
		this.fieldValue = fieldValue;
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

	public int getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(int fieldValue) {
		this.fieldValue = fieldValue;
	}

}
