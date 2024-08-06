package com.akshay.bankSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.bankSystem.dto.UserDto;
import com.akshay.bankSystem.exceptions.ApiException;
import com.akshay.bankSystem.payloads.request.JwtRequest;
import com.akshay.bankSystem.payloads.request.SignupRequest;
import com.akshay.bankSystem.payloads.response.JwtResponse;
import com.akshay.bankSystem.security.JwtTokenHelper;
import com.akshay.bankSystem.services.UserServices;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

	@Autowired
	private UserServices service;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	public ResponseEntity<UserDto> creatUser(@RequestBody SignupRequest user) {
		UserDto u = service.createUser(user);
		if (u == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> userLogin(@RequestBody JwtRequest request) throws Exception {

		this.authenticate(request.getUserName(), request.getPassword());

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUserName());
		if (userDetails == null) {
			throw new BadCredentialsException("Username is Invalide");
		}

		String generateTokenString = this.jwtTokenHelper.generateToken(userDetails);

		JwtResponse response = new JwtResponse();
		response.setToken(generateTokenString);
		System.out.println(generateTokenString);

		return ResponseEntity.status(200).body(response);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			if (this.userDetailsService.loadUserByUsername(username) == null) {
				throw new BadCredentialsException("Username is Invalide");
			}

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					password);

			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invalid Details !!");
			throw new ApiException("Invalid username and password !!");
		}

	}

}
