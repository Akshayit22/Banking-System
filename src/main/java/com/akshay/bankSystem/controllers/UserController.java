package com.akshay.bankSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.bankSystem.dto.AddressDto;
import com.akshay.bankSystem.dto.UserDetailsDto;
import com.akshay.bankSystem.dto.UserDto;
import com.akshay.bankSystem.dto.UserInfo;
import com.akshay.bankSystem.payloads.request.SignupRequest;
import com.akshay.bankSystem.services.SecuredRestController;
import com.akshay.bankSystem.services.UserServices;

@RestController
public class UserController implements SecuredRestController{

	@Autowired
	private UserServices service;
	
	
	/*---------------------- User Routes ------------------------*/
	
	@PutMapping("/user/{username}")
	public ResponseEntity<UserDto> updateUser(@PathVariable String username,@RequestBody SignupRequest user){
		
		UserDto u = service.updateUser(username, user);
		if (u == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
	
	@PostMapping("/user/userdetails/{username}")
	public ResponseEntity<UserDetailsDto> createUserDetails(@PathVariable String username,@RequestBody UserDetailsDto details){
		
		UserDetailsDto updated = service.createUserDetails(username, details);
		if (updated == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(updated);
	}
	
	
	@PutMapping("/user/userdetails/{username}")
	public ResponseEntity<UserDetailsDto> updateUserDetails(@PathVariable String username,@RequestBody UserDetailsDto user){
		
		UserDetailsDto u = service.updateUserDetails(username, user);
		if (u == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
	@PostMapping("/user/address/{username}")
	public ResponseEntity<AddressDto> createAddress(@PathVariable String username,@RequestBody AddressDto address){
		
		AddressDto newAddress = service.createAddress(username, address);
		if (newAddress == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
	}
	
	@PutMapping("/user/address/{username}")
	public ResponseEntity<AddressDto> updateAddress(@PathVariable String username,@RequestBody AddressDto address){
		
		AddressDto newAddress = service.updateAddress(username, address);
		if (newAddress == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
	}
	
	@GetMapping("/user/details/{username}")
	public ResponseEntity<UserInfo> getUserInfo(@PathVariable String username){
		
		UserInfo info = this.service.getUserInformation(username);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(info);
	}
	
	
	/*---------------------- Bank Routes ------------------------*/
	
	

	@GetMapping("/bank/users")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.status(200).body(service.getAllUsers());
	}
	
	@GetMapping("/bank/user/id/{user_id}")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<UserDto> userByUserId(@PathVariable int user_id) {
		UserDto u = service.getUserById(user_id);
		if(u == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(u);
	}
		
	
	@PostMapping("/bank/user")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<UserDto> creatUser(@RequestBody SignupRequest user) {
		UserDto u = service.createUser(user);
		if (u == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
	/*---------------------- Admin Routes ------------------------*/
	
	
	@DeleteMapping("/admin/user/{user_id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public HttpStatus deleteUser(@PathVariable int user_id) {
		if(service.deleteUser(user_id)) {
			return HttpStatus.ACCEPTED;
		}
		return HttpStatus.NOT_MODIFIED;
	}
	

	
}



