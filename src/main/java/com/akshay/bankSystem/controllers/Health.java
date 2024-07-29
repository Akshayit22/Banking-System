package com.akshay.bankSystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {
	
	@GetMapping("/")
	public String Home() {
		return "Welcome to Bank Application Api.....";
	}

	@GetMapping("/health-check")
	public HttpStatus healthCheck() {
		return HttpStatus.OK;
	}
	
	@GetMapping("/error")
	public ResponseEntity<String> Error() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in Url... No such url exists...");
	}
}
