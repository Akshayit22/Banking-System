package com.akshay.bankSystem.services;

import java.util.List;

import com.akshay.bankSystem.entities.Account;
import com.akshay.bankSystem.entities.Transaction;
import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.payloads.TransactionPayload;

public interface TransactionServices {

	public Transaction depositeMoney(TransactionPayload details);
	
	public Transaction WithdrawMoney(TransactionPayload details);
	
	public Transaction TransferMoney(TransactionPayload details);
	
	public Transaction createFailedTransaction(Transaction transactionDetails);
	
	public Account getAccountByAccount(int accountNumber);
	
	public User getUserByUserId(int userId);
	
	public List<Transaction> getAllTransactions();
	
	public List<Transaction> getAccountTransactions(int accountNumber);
	
}
