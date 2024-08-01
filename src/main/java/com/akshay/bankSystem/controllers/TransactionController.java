package com.akshay.bankSystem.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.bankSystem.entities.Transaction;
import com.akshay.bankSystem.payloads.request.TransactionPayload;
import com.akshay.bankSystem.services.TransactionServices;

@RestController
public class TransactionController {
	
	@Autowired
	private TransactionServices service;
	
	@PostMapping("/transaction/deposit")
	public ResponseEntity<Transaction> deposit(@RequestBody Map<String, Integer> details) {
		Transaction transaction = service.depositeMoney(new TransactionPayload(details.get("AccountNumber"),details.get("UserId"),details.get("Amount")));
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(transaction);
	}

	@PostMapping("/transaction/withdraw")
	public ResponseEntity<Transaction> withdraw(@RequestBody Map<String, Integer> details) {
		Transaction transaction = service.WithdrawMoney(new TransactionPayload(details.get("AccountNumber"),details.get("UserId"),details.get("Amount")));
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(transaction);
	}

	@PostMapping("/transaction/transfer")
	public ResponseEntity<Transaction> transfer(@RequestBody Map<String, Integer> details) {
		Transaction transaction = service.TransferMoney(new TransactionPayload(details.get("AccountNumber"),details.get("UserId"),details.get("Amount"),details.get("TargetAccountNumber")));
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(transaction);
	}
	
	@GetMapping("/bank/transactions")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<Transaction>> getAllTransactons(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllTransactions());
	}
	
	@GetMapping("/transaction/account/{accountNumber}")
	public ResponseEntity<List<Transaction>> getAllTransactons(@PathVariable int accountNumber){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAccountTransactions(accountNumber));
	}

}
//(deposit/withdraw/transfer)