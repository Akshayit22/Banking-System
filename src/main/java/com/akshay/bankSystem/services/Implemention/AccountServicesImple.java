package com.akshay.bankSystem.services.Implemention;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshay.bankSystem.entities.Account;
import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.exceptions.ResourceNotFoundException;
import com.akshay.bankSystem.repositories.AccountRespository;
import com.akshay.bankSystem.repositories.UserRepository;
import com.akshay.bankSystem.services.AccountServices;

@Service
public class AccountServicesImple implements AccountServices{
	
	@Autowired
	private AccountRespository accountRespository;

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Override
	public Account createAccount(int userId, Account account) {
		User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("account", "Id", userId));
		
		//account.setSecurityPin(passwordEncoder.encode(account.getSecurityPin()));
		account.setInterestRate(account.getAccountType().equalsIgnoreCase("saving")?5.5:3.3);
		account.setUser(user);
		
		List<Account> userAccountList = user.getAccounts();
		userAccountList.add(account);
		user.setAccounts(userAccountList);
		
		accountRespository.save(account);
		
		
		return account;
	}
	
	@Override
	public Account updateAccount(int userId, Account account) {
		
			Account accountUpdate = accountRespository.findByAccountNumber(account.getAccountNumber()).get(0);
			
			User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
			
			accountUpdate.setName(account.getName());
			accountUpdate.setMobile(account.getMobile());
			accountUpdate.setBalance(account.getBalance());
			accountUpdate.setAccountType(account.getAccountType());
			accountUpdate.setCreatedAt(accountUpdate.getCreatedAt());
			accountUpdate.setInterestRate(accountUpdate.getAccountType().equalsIgnoreCase("saving")?5.5:3.3);
			accountUpdate.setUpdatedAt(new Date());
			accountUpdate.setSecurityPin(account.getSecurityPin());
			//accountUpdate.setSecurityPin(passwordEncoder.encode(account.getSecurityPin()));
			accountUpdate.setUser(user);
			
			Account newAccount = accountRespository.save(accountUpdate);
			
			
			return newAccount;

	}
	
	@Override
	public List<Account> getAllAccounts(){
		return accountRespository.findAll();
	}
	
	@Override
	public Account getAccountByAccountNumber(int accountNumber) {
		//Account account = accountRespository.findById(accountNumber).orElseThrow(()-> new ResourceNotFoundException("Account", "AccountNumber", accountNumber));
		//return account;
		try {
			List<Account> accounts = accountRespository.findByAccountNumber(accountNumber);
			
			if(accounts.isEmpty() || accounts.size() == 0)
				return null;
			return accounts.get(0);
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Account> getAccountsByUserId(int userId) {
		
		try {
			User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Account", "AccountNumber", userId));
			List<Account> accounts =  user.getAccounts();
			if(accounts.isEmpty() || accounts.size() == 0)
				return null;
			return accounts;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
