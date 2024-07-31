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
public class LoanDto {
	
	private int loanAmount;
	
	private String loanType;
	
	private String loanStatus;
	
	
}
