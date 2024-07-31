package com.akshay.bankSystem.services;

import java.util.List;

import com.akshay.bankSystem.dto.AddressDto;
import com.akshay.bankSystem.dto.UserDetailsDto;
import com.akshay.bankSystem.dto.UserDto;
import com.akshay.bankSystem.dto.UserInfo;
import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.payloads.SignupRequest;

public interface UserServices {
	
	public UserDto createUser(SignupRequest user);
	
	public UserDto updateUser(String username,SignupRequest user);
	
	public UserDetailsDto createUserDetails(String username,UserDetailsDto details);
	
	public UserDetailsDto updateUserDetails(String username,UserDetailsDto details);
	
	public AddressDto createAddress(String username, AddressDto address);
	
	public AddressDto updateAddress(String username, AddressDto address);
	
	public UserInfo getUserInformation(String username);
	
	
	
	public List<User> getAllUsers();
	
	
	public User getUserById(int user_id);
	
	public User getUserByUsername(String username);
	
	
	public boolean deleteUser(int user_id);
	
}
