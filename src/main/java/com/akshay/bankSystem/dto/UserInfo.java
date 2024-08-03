package com.akshay.bankSystem.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserInfo {

	private UserDto userDto;

	private List<AddressDto> addressDtos;

	private UserDetailsDto userDetailsDto;

	public UserInfo(UserDto userDto, List<AddressDto> addressDtos, UserDetailsDto userDetailsDto) {
		super();
		this.userDto = userDto;
		this.addressDtos = addressDtos;
		this.userDetailsDto = userDetailsDto;
	}

	public UserInfo() {
		super();
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}

	public void setAddressDtos(List<AddressDto> addressDtos) {
		this.addressDtos = addressDtos;
	}

	public UserDetailsDto getUserDetailsDto() {
		return userDetailsDto;
	}

	public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
		this.userDetailsDto = userDetailsDto;
	}

}
