package com.akshay.bankSystem.payloads;

import lombok.Data;

@Data
public class TransactionPayload {
	
	private int AccountNumber;
	
	private int UserId;
	
	private int TargetAccountNumber;
	
	private int Amount;

	public TransactionPayload(int accountNumber, int userId, int amount, int targetAccountNumber) {
		super();
		AccountNumber = accountNumber;
		UserId = userId;
		Amount = amount;
		TargetAccountNumber = targetAccountNumber;
	}
	
	public TransactionPayload(int accountNumber, int userId, int amount) {
		super();
		AccountNumber = accountNumber;
		UserId = userId;
		Amount = amount;
	}

	public TransactionPayload() {
		super();
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getTargetAccountNumber() {
		return TargetAccountNumber;
	}

	public void setTargetAccountNumber(int targetAccountNumber) {
		TargetAccountNumber = targetAccountNumber;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	@Override
	public String toString() {
		return "TransactionPayload [AccountNumber=" + AccountNumber + ", UserId=" + UserId + ", TargetAccountNumber="
				+ TargetAccountNumber + ", Amount=" + Amount + "]";
	}
	
	
}
