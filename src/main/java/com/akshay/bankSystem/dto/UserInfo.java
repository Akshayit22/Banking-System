package com.akshay.bankSystem.dto;

import lombok.Data;


@Data
public class UserInfo {
	
	private UserDto userDto;
	
	private AddressDto addressDto;
	
	private UserDetailsDto userDetailsDto;
	

	public UserInfo() {
		super();
	}
	
	

	public UserInfo(UserDto userDto, AddressDto addressDto, UserDetailsDto userDetailsDto) {
		super();
		this.userDto = userDto;
		this.addressDto = addressDto;
		this.userDetailsDto = userDetailsDto;
	}



	public UserDto getUserDto() {
		return userDto;
	}


	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}


	public AddressDto getAddressDto() {
		return addressDto;
	}


	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}


	public UserDetailsDto getUserDetailsDto() {
		return userDetailsDto;
	}


	public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
		this.userDetailsDto = userDetailsDto;
	}

	
	
}
