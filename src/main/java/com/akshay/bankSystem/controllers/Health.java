package com.akshay.bankSystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {

	@GetMapping("/health-check")
	public HttpStatus healthCheck() {
		return HttpStatus.OK;
	}
	
	@GetMapping("/error")
	public HttpStatus Error() {
		return HttpStatus.BAD_REQUEST;
	}
}
