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
public class UserDetailsDto {
	
	private String name;
	
	private String mobile;
	
	private String gender;
	
	private int age;

}
