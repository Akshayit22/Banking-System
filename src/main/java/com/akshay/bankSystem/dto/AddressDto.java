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
public class AddressDto {
	
	private String street;
	
	private String area;
	
	private String city;
	
	private int pincode;	
	
}
