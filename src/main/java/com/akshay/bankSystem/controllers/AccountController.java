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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.bankSystem.dto.AccountDetails;
import com.akshay.bankSystem.dto.AccountDto;
import com.akshay.bankSystem.dto.NomineeDto;
import com.akshay.bankSystem.services.AccountServices;

@RestController
public class AccountController {

	@Autowired
	private AccountServices service;

	/*----------------- Account routes ---------------------*/

	@PostMapping("/account/user/{username}")
	public ResponseEntity<AccountDto> createAccount(@PathVariable String username, @RequestBody AccountDto account) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(username, account));
	}

	@PutMapping("/account/user/{username}")
	public ResponseEntity<AccountDto> updateAccount(@PathVariable String username, @RequestBody AccountDto account) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.updateAccount(username, account));
	}

	@GetMapping("/account/user/{username}")
	public ResponseEntity<List<AccountDto>> getAccountByUserId(@PathVariable String username) {
		List<AccountDto> accounts = service.getAccountsByUsername(username);
		return ResponseEntity.status(HttpStatus.OK).body(accounts);

	}

	@GetMapping("/account/{accountNumber}")
	public ResponseEntity<AccountDto> getAccountByAccountNumber(@PathVariable int accountNumber) {
		AccountDto account = service.getAccountByAccountNumber(accountNumber);
		if (account == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(account);
	}

	@GetMapping("/account/details/{accountNumber}")
	public ResponseEntity<AccountDetails> getAccountDetails(@PathVariable int accountNumber) {

		return ResponseEntity.status(200).body(this.service.getAccountDetails(accountNumber));
	}

	/*---------------------- Account - Nominee --------------*/

	@PostMapping("/account/nominee/{accountnumber}")
	public ResponseEntity<NomineeDto> addNominee(@RequestParam int accountNumber, @RequestBody NomineeDto nominee) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.createNominee(accountNumber, nominee));
	}

	@PutMapping("/account/nominee/{accountnumber}")
	public ResponseEntity<NomineeDto> updateNominee(@RequestParam int accountNumber, @RequestBody NomineeDto nominee) {

		return ResponseEntity.status(200).body(service.updateNominee(accountNumber, nominee));
	}

	/*----------------- bank : Account route ---------------------*/

	@GetMapping("/bank/accounts")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllAccounts());
	}

	@GetMapping("/bank/nominees")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<NomineeDto>> getAllNominees() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllNominees());
	}

	@DeleteMapping("/bank/account")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public boolean deleteAccount() {

		return true;
	}

}
