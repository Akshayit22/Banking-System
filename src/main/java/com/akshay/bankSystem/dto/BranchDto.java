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
public class BranchDto {
	
	private String name;
	
	private String ifscCode;
	
	private String address;
	
	private int pincode;

}
