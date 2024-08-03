package com.akshay.bankSystem.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Health {

	private static final Logger logger = LoggerFactory.getLogger(Health.class);

	@GetMapping("/")
	public String Home() {
		logger.warn("Test Warning..");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return username + " Welcome to Bank Application Api.....";
	}

	@GetMapping("/health-check")
	public HttpStatus healthCheck() {
		logger.error("health-check-Ok... {}%", 101);
		return HttpStatus.OK;
	}

	@GetMapping("/error")
	public ResponseEntity<String> Error() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in Url... No such url exists...");
	}
}
