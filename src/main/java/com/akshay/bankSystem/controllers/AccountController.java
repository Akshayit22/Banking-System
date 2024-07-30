package com.akshay.bankSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.bankSystem.entities.Account;
import com.akshay.bankSystem.services.AccountServices;

@RestController
public class AccountController {

	@Autowired
	private AccountServices service;
	
	@PostMapping("/account/user/{userId}")
	public ResponseEntity<Account> createAccount(@PathVariable int userId,@RequestBody Account account) {
		System.out.println(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(userId, account));
	}
	
	@PutMapping("/account/user/{userId}")
	public ResponseEntity<Account> updateAccount(@PathVariable int userId,@RequestBody Account account) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.updateAccount(userId, account));
	}
	
	@GetMapping("/bank/accounts")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<Account>> getAllAccounts(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllAccounts());
	}
	
	@GetMapping("/account/user/{userId}")
	public ResponseEntity<List<Account>> getAccountByUserId(@PathVariable int userId) {
		List<Account> accounts = service.getAccountsByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(accounts);
		
	}
	
	@GetMapping("/account/{accountNumber}")
	public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable int accountNumber) {
		Account account = service.getAccountByAccountNumber(accountNumber);
		if(account == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(account);
	}
	
}
