package com.akshay.bankSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
	
	private int loanAmount;
	
	private String loanType;
	
	@Schema(accessMode = AccessMode.READ_ONLY)
	private String loanStatus;
	
	

	public LoanDto() {
		super();
	}

	public LoanDto(int loanAmount, String loanType, String loanStatus) {
		super();
		this.loanAmount = loanAmount;
		this.loanType = loanType;
		this.loanStatus = loanStatus;
	}

	
	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	@Override
	public String toString() {
		return "LoanDto [loanAmount=" + loanAmount + ", loanType=" + loanType + ", loanStatus=" + loanStatus + "]";
	}
	
	
	
	
}
