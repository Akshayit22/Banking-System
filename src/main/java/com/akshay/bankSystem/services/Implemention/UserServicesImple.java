package com.akshay.bankSystem.services.Implemention;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.akshay.bankSystem.repositories.UserDetailsRepository;
import com.akshay.bankSystem.repositories.AddressRepository;
import com.akshay.bankSystem.repositories.UserRepository;
import com.akshay.bankSystem.services.UserServices;
import com.akshay.bankSystem.dto.AddressDto;
import com.akshay.bankSystem.dto.UserDetailsDto;
import com.akshay.bankSystem.dto.UserDto;
import com.akshay.bankSystem.dto.UserInfo;
import com.akshay.bankSystem.entities.Address;
import com.akshay.bankSystem.entities.BankUserDetails;
import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.exceptions.ResourceNotFoundException;
import com.akshay.bankSystem.payloads.SignupRequest;

@Service
public class UserServicesImple implements UserServices {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserDetailsRepository userDetailsRepo;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	/*---------------------- User Services ------------------------*/
	
	
	@Override
	public UserDto createUser(SignupRequest userData) {
		
		User user = this.modelMapper.map(userData, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		UserDto userDto =  this.modelMapper.map(userRepo.save(user), UserDto.class);
		
		return userDto;
	}
	
	
	
	@Override
	public UserDto updateUser(String username, SignupRequest user) {

		User userUpdate =  this.getUserByUsername(username);
		
		userUpdate.setUserName(user.getUserName());
		userUpdate.setPassword(this.passwordEncoder.encode(user.getPassword()));
		userUpdate.setUpdatedAt(new Date());
		
		User updated = userRepo.save(userUpdate);
		
		return this.modelMapper.map(updated, UserDto.class);

	}
	
	@Override
	public UserDetailsDto createUserDetails(String username,UserDetailsDto details) {
		
		User user =  this.getUserByUsername(username);
		
		BankUserDetails newDetails = this.modelMapper.map(details, BankUserDetails.class);
		newDetails.setUser(user);
		
		BankUserDetails saveDetails = userDetailsRepo.save(newDetails);
		
		return this.modelMapper.map(saveDetails, UserDetailsDto.class);
	}
	
	@Override
	public UserDetailsDto updateUserDetails(String username,UserDetailsDto details) {
		
		BankUserDetails detailsUpdate = this.getUserDetailsByUsername(username);
		
		detailsUpdate.setName(details.getName());
		detailsUpdate.setGender(details.getGender());
		detailsUpdate.setMobile(details.getMobile());
		detailsUpdate.setAge(details.getAge());
		detailsUpdate.setUpdatedAt(new Date());
		
		BankUserDetails updatedDetails = userDetailsRepo.save(detailsUpdate);
		
		return this.modelMapper.map(updatedDetails, UserDetailsDto.class);
	}
	
	
	public AddressDto createAddress(String username, AddressDto address) {
		
		User user =  this.getUserByUsername(username);
		
		Address newAddress = this.modelMapper.map(address, Address.class);
		newAddress.setUser(user);
		
		return this.modelMapper.map(addressRepository.save(newAddress), AddressDto.class);
	}
	
	
	public AddressDto updateAddress(String username, AddressDto address) {
		
		Address addressUpdate = this.getAddressByUsername(username);
		
		addressUpdate.setStreet(address.getStreet());
		addressUpdate.setArea(address.getArea());
		addressUpdate.setCity(address.getCity());
		addressUpdate.setPincode(address.getPincode());
		addressUpdate.setUpdatedAt(new Date());
		
		Address newAddress = addressRepository.save(addressUpdate);
		
		return this.modelMapper.map(newAddress, AddressDto.class);
	}
	
	@Override
	public UserInfo getUserInformation(String username) {
		
		UserDto user =  this.modelMapper.map(this.getUserByUsername(username),UserDto.class);
		AddressDto address = this.modelMapper.map(this.getAddressByUsername(username),AddressDto.class);
		UserDetailsDto details = this.modelMapper.map(this.getUserDetailsByUsername(username),UserDetailsDto.class);
		
		UserInfo info = new UserInfo(user,address,details);
		
		return info;
	}
	
	
	
	
	/*-------------------- Helper Functions --------------------------- */
	
	public BankUserDetails getUserDetailsByUsername(String username) {
//		
//		User user =  this.getUserByUsername(username);
//		
//		List<BankUserDetails> list = userDetailsRepo.findByUser(user);
//		
//		if(list == null || list.isEmpty()) {
//			throw new ResourceNotFoundException("username", "username", username);
//		}
//		
//		return list.get(0);
		return userDetailsRepo.getDetailsByUsername(username);
	}
	
	public Address getAddressByUsername(String username) {
		
		
//		Address address = addressRepository.getAddressByUsername(username);
//		System.out.println(address);
//		
//		
//		User user =  this.getUserByUsername(username);
//		
//		List<Address> list = addressRepository.findByUser(user);
//		
//		if(list == null || list.isEmpty()) {
//			throw new ResourceNotFoundException("username", "username", username);
//		}
//		
//		return list.get(0);
		return addressRepository.getAddressByUsername(username);
	}
	
	@Override
	public User getUserByUsername(String username) {
		User user = userRepo.getUserByUsername(username);
		if(user == null) {
			throw new ResourceNotFoundException("username", "username", username);
		}
		return user;
	}

	@Override
	public User getUserById(int user_id) {
		User u =  userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("user", "Id", user_id));
		
		return u;
	}
	
	
	@Override
	public boolean deleteUser(int user_id) {
			User u =  userRepo.findById(user_id).
					orElseThrow(()-> new ResourceNotFoundException("user", "Id", user_id));
			userRepo.delete(u);
			return true;
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	

}
