package com.akshay.bankSystem.services;

import java.util.List;

import com.akshay.bankSystem.entities.Account;

public interface AccountServices {

	public Account createAccount(int userId, Account account);
	
	public Account updateAccount(int userId, Account account);
	
	public List<Account> getAllAccounts();
	
	public Account getAccountByAccountNumber(int accountNumber);
	
	public List<Account> getAccountsByUserId(int userId);
	
	
	
}
