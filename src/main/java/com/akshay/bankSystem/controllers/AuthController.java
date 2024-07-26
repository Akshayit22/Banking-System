package com.akshay.bankSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.payloads.JwtRequest;
import com.akshay.bankSystem.payloads.JwtResponse;
import com.akshay.bankSystem.services.UserServices;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserServices service;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> userLogin(@RequestBody JwtRequest request) throws Exception{
		return ResponseEntity.status(200).body(null);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<User> creatUser(@RequestBody User user) {
		User u = service.createUser(user);
		if (u == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
}
