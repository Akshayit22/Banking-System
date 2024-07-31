package com.akshay.bankSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	
	private int accountNumber;
	
	private String accountType;
	
	private int balance;
	
	private String securityPin;
	
	private double interestRate;
	
}
