package com.akshay.bankSystem.dto;

import java.util.Date;

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
public class TransactionDto {
	
	private int transactionId; 
	
	private String transactionType;
	
	private String status;

	private int amount;
	
	private Date createdAt;
}
