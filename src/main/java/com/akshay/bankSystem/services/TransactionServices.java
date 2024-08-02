package com.akshay.bankSystem.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.akshay.bankSystem.entities.Transaction;
import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.payloads.request.TransactionPayload;

public interface TransactionServices {

	public Transaction depositeMoney(TransactionPayload details);
	
	public Transaction WithdrawMoney(TransactionPayload details);
	
	public Transaction TransferMoney(TransactionPayload details);
	
		
	public List<Transaction> getAllTransactions();
	
	public List<Transaction> getAccountTransactions(int accountNumber);
	
	public CompletableFuture<List<User>> sample();
	
}
