package com.akshay.bankSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.akshay.bankSystem.dto.LoanDto;
import com.akshay.bankSystem.services.LoanService;

@RestController
public class LoanController {

	@Autowired
	private LoanService loanService;

	/*---------------------- Account - Loan --------------*/

	@PostMapping("/loan/account/{accountNumber}/user/{username}")
	public ResponseEntity<LoanDto> createLoan(@PathVariable(required = true) int accountNumber,
			@PathVariable String username, @RequestBody(required = true) LoanDto details) {

		return ResponseEntity.status(200).body(this.loanService.createLoan(accountNumber, username, details));
	}

	@PutMapping("/loan/account/{accountNumber}/user/{username}")
	public ResponseEntity<LoanDto> updateLoan(@PathVariable(required = true) int accountNumber,
			@PathVariable String username, @RequestBody LoanDto details) {

		return ResponseEntity.status(200).body(this.loanService.updateLoan(accountNumber, username, details));
	}

	@GetMapping("/loan/account/user/{username}")
	public ResponseEntity<List<LoanDto>> getloansByUsername(@PathVariable String username) {

		return ResponseEntity.status(200).body(this.loanService.getLoanByUsername(username));
	}

	@GetMapping("/loan/account/{accountNumber}")
	public ResponseEntity<List<LoanDto>> getloansByAccountNumber(@RequestParam int accountNumber) {

		return ResponseEntity.status(200).body(this.loanService.getLoanByAccount(accountNumber));
	}

	/*---------------------- Bank - Loan --------------*/

	@GetMapping("/bank/loans")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<LoanDto>> getAllloans() {

		return ResponseEntity.status(200).body(this.loanService.getAllloans());
	}

	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	@PutMapping("/bank/loan/{loanId}")
	public ResponseEntity<Boolean> changeLoanStatus(@RequestParam int loanId, @RequestBody Boolean result) {

		return ResponseEntity.status(200).body(this.loanService.changeLoanStatus(loanId, result));
	}

}
