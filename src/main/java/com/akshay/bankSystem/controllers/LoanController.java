package com.akshay.bankSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.akshay.bankSystem.dto.LoanDto;
import com.akshay.bankSystem.services.LoanService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", methods = { RequestMethod.GET,
		RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class LoanController {

	@Autowired
	private LoanService loanService;

	/*---------------------- Account - Loan --------------*/

	@PostMapping("/loan/account/{accountNumber}")
	public ResponseEntity<LoanDto> createLoan(@PathVariable(required = true) int accountNumber,
			@RequestBody(required = true) LoanDto details) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return ResponseEntity.status(200).body(this.loanService.createLoan(accountNumber, username, details));
	}

	@PutMapping("/loan/account/{accountNumber}/loan/{loanId}")
	public ResponseEntity<LoanDto> updateLoan(@PathVariable(required = true) int accountNumber,
			@PathVariable(required = true) int loanId, @RequestBody LoanDto details) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return ResponseEntity.status(200).body(this.loanService.updateLoan(username, accountNumber, loanId, details));
	}

	@GetMapping("/loan/account")
	public ResponseEntity<List<LoanDto>> getloansByUsername() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return ResponseEntity.status(200).body(this.loanService.getLoanByUsername(username));
	}

	@GetMapping("/loan/account/{accountNumber}")
	public ResponseEntity<List<LoanDto>> getloansByAccountNumber(@PathVariable(required = true) int accountNumber) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return ResponseEntity.status(200).body(this.loanService.getLoanByAccount(username, accountNumber));
	}

	/*---------------------- Bank - Loan --------------*/

	@GetMapping("/bank/loans")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<LoanDto>> getAllloans() {

		return ResponseEntity.status(200).body(this.loanService.getAllloans());
	}

	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	@PutMapping("/bank/loan/{loanId}")
	public ResponseEntity<Boolean> changeLoanStatus(@PathVariable(required = true) int loanId,
			@RequestBody Boolean result) {

		return ResponseEntity.status(200).body(this.loanService.changeLoanStatus(loanId, result));
	}

}
