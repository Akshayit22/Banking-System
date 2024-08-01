package com.akshay.bankSystem.payloads.request;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
public class TransactionPayload {

	@Schema(requiredMode = RequiredMode.REQUIRED)
	private int accountNumber;

	@Schema(requiredMode = RequiredMode.NOT_REQUIRED)
	private int targetAccountNumber;

	@Schema(requiredMode = RequiredMode.REQUIRED)
	private int amount;

	@Schema(requiredMode = RequiredMode.REQUIRED)
	private String userName;

	@Schema(requiredMode = RequiredMode.REQUIRED)
	private String securityPin;

	@Schema(accessMode = AccessMode.READ_ONLY)
	private Date timeStamp;

	public TransactionPayload() {
		super();
	}

	public TransactionPayload(int accountNumber, int targetAccountNumber, int amount, String userName,
			String securityPin) {
		super();
		this.accountNumber = accountNumber;
		this.targetAccountNumber = targetAccountNumber;
		this.amount = amount;
		this.userName = userName;
		this.securityPin = securityPin;
	}

	public TransactionPayload(int accountNumber, int amount, String userName, String securityPin) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.userName = userName;
		this.securityPin = securityPin;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getTargetAccountNumber() {
		return targetAccountNumber;
	}

	public void setTargetAccountNumber(int targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSecurityPin() {
		return securityPin;
	}

	public void setSecurityPin(String securityPin) {
		this.securityPin = securityPin;
	}

	@Override
	public String toString() {
		return "TransactionPayload [accountNumber=" + accountNumber + ", targetAccountNumber=" + targetAccountNumber
				+ ", amount=" + amount + ", userName=" + userName + ", securityPin=" + securityPin + ", timeStamp="
				+ timeStamp + "]";
	}

}
