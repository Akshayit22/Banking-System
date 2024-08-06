package com.akshay.bankSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.bankSystem.entities.Transaction;
import com.akshay.bankSystem.exceptions.ApiException;
import com.akshay.bankSystem.payloads.request.TransactionPayload;
import com.akshay.bankSystem.services.TransactionServices;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class TransactionController {

	@Autowired
	private TransactionServices service;

	@PostMapping("/transaction/deposit")
	public ResponseEntity<Transaction> deposit(@RequestBody TransactionPayload details) {
		if (details.getAmount() < 0)
			throw new ApiException("invalide amount.");

		Transaction transaction = service.depositeMoney(details);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(transaction);
	}

	@PostMapping("/transaction/withdraw")
	public ResponseEntity<Transaction> withdraw(@RequestBody TransactionPayload details) {
		if (details.getAmount() < 0)
			throw new ApiException("invalide amount.");

		Transaction transaction = service.WithdrawMoney(details);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(transaction);
	}

	@PostMapping("/transaction/transfer")
	public ResponseEntity<Transaction> transfer(@RequestBody TransactionPayload details) {
		if (details.getAmount() < 0)
			throw new ApiException("invalide amount.");

		Transaction transaction = service.TransferMoney(details);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(transaction);
	}

	@GetMapping("/transaction/account/{accountNumber}")
	public ResponseEntity<List<Transaction>> getAllTransactons(@PathVariable int accountNumber) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAccountTransactions(accountNumber));
	}

	@GetMapping("/bank/transactions")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<Transaction>> getAllTransactons() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllTransactions());
	}

}