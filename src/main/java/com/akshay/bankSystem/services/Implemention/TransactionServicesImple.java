package com.akshay.bankSystem.services.Implemention;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akshay.bankSystem.configs.Constants;
import com.akshay.bankSystem.dto.TransactionDto;
import com.akshay.bankSystem.entities.Account;
import com.akshay.bankSystem.entities.Transaction;
import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.exceptions.ApiException;
import com.akshay.bankSystem.exceptions.ResourceNotFoundException;
import com.akshay.bankSystem.exceptions.TransactionException;
import com.akshay.bankSystem.payloads.request.TransactionPayload;
import com.akshay.bankSystem.repositories.AccountRespository;
import com.akshay.bankSystem.repositories.TransactionRepository;
import com.akshay.bankSystem.repositories.UserRepository;
import com.akshay.bankSystem.services.AccountServices;
import com.akshay.bankSystem.services.TransactionServices;

@Service
@Transactional
public class TransactionServicesImple implements TransactionServices{

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountRespository accountRespository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper modelMapper;
	
	
	/*-------------------------- Transaction service ------------------------------*/
	
	@Override
	@Transactional
	public Transaction depositeMoney(TransactionPayload details) {
		
		Account account = this.getAccountByAccountNumber(details.getAccountNumber());
		User user = this.getUserByUsername(details.getUserName());
				
		String pin = details.getSecurityPin();
		int newBalance = account.getBalance() + details.getAmount();
		
		securityChecks(account,user,newBalance,pin,details.getUserName());
		
		Transaction transaction = this.getNewTransaction(details.getAmount(),Constants.DEPOSIT_TRANSACTION);
		transaction.setAccount(account);
		Transaction pendingTransaction = transactionRepository.save(transaction);
		
		account.setBalance(newBalance);
		accountRespository.save(account);
		
		pendingTransaction.setStatus(Constants.STATUS_SUCCESS);
		pendingTransaction.setMessage("SUCCESS");
		
		transaction = this.transactionRepository.save(pendingTransaction);
		return transaction;
	}
	
	@Override
	@Transactional
	public Transaction WithdrawMoney(TransactionPayload details) {
		
		Account account = this.getAccountByAccountNumber(details.getAccountNumber());
		User user = this.getUserByUsername(details.getUserName());
				
		String pin = details.getSecurityPin();
		int newBalance = account.getBalance() - details.getAmount();
		
		securityChecks(account,user,newBalance,pin,details.getUserName());
		
		Transaction transaction = this.getNewTransaction(details.getAmount(),Constants.WITHDRAW_TRANSACTION);
		transaction.setAccount(account);
		Transaction pendingTransaction = transactionRepository.save(transaction);
		
		account.setBalance(newBalance);
		accountRespository.save(account);
		
		pendingTransaction.setStatus(Constants.STATUS_SUCCESS);
		pendingTransaction.setMessage("SUCCESS");
		
		transaction = this.transactionRepository.save(pendingTransaction);
		return transaction;
	}
	
	@Override
	@Transactional
	public Transaction TransferMoney(TransactionPayload details) {
		
		Account account = this.getAccountByAccountNumber(details.getAccountNumber());
		Account targetAccount = this.getAccountByAccountNumber(details.getTargetAccountNumber());
		User user = this.getUserByUsername(details.getUserName());
				
		String pin = details.getSecurityPin();
		int newBalance = account.getBalance() - details.getAmount();
		
		securityChecks(account,user,newBalance,pin,details.getUserName());
		
		//  sender transaction 
		Transaction transaction = this.getNewTransaction(details.getAmount(),Constants.MONEY_TRANSFER);
		transaction.setAccount(account);
		Transaction pendingTransaction = transactionRepository.save(transaction);
		
		account.setBalance(newBalance);
		accountRespository.save(account);
		
		targetAccount.setBalance(details.getAmount() + targetAccount.getBalance());
		accountRespository.save(targetAccount);
		
		
		pendingTransaction.setStatus(Constants.STATUS_SUCCESS);
		pendingTransaction.setMessage("SUCCESS");
		
		transaction = this.transactionRepository.save(pendingTransaction);
		
		Transaction receiverTrnsaction = this.getNewTransaction(details.getAmount(), Constants.MONEY_TRANSFER);
		receiverTrnsaction.setAccount(targetAccount);
		receiverTrnsaction.setStatus(Constants.MONEY_RECEIVED+targetAccount.getAccountNumber());
		receiverTrnsaction.setMessage("Amount Received.");
		
		this.transactionRepository.save(receiverTrnsaction);
		
		return transaction;
	}
	
	public void securityChecks(Account account,User user, int newBalance, String securityPin,String username) {
		if(newBalance < 0) {
			throw new TransactionException( "Insufisiant balance.",newBalance);
		}
		
		if (account.getAccountType().equalsIgnoreCase("Saving") && Constants.SAVING_MIN_BALANCE > newBalance) {
			
			throw new ApiException("Minimum balance of " + Constants.SAVING_MIN_BALANCE + " should maintained in saving Account.");
		}
		
		String pinEncoded = passwordEncoder.encode(securityPin);
		System.out.println(pinEncoded);
		
		if(!passwordEncoder.matches(securityPin, account.getSecurityPin())) {
			throw new ApiException("Security pin is Incorrect.");
		}
		
		if(!account.getUser().getUserName().equals(username)) {
			throw new ApiException("User with this user name does not own account with this account number.");
		}
		
	}
	
	public Transaction getNewTransaction(int amount,String type) {
		return new Transaction(amount,type,Constants.STATUS_PENDING,Constants.STATUS_PENDING);
	}
	
	public Account getAccountByAccountNumber(int accountNumber) {
		Account account = accountRespository.findById(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber));
		return account;
	}
	
	public User getUserByUsername(String username) {
		User user = this.userRepository.getUserByUsername(username);
		if(user == null)
			throw new ResourceNotFoundException("Username Not Found.", "username", username);
		return user;
	}
	

	
	

	@Override
	public CompletableFuture<List<User>> sample() {
		System.out.println("PRINTING THREAD NAME ------------------->"+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture(userRepository.findAll());
	}
	

	@Override
	public List<Transaction> getAllTransactions(){
		return transactionRepository.findAll();
	}
	
	@Override
	public List<Transaction> getAccountTransactions(int accountNumber){
		return transactionRepository.findByAccount(this.getAccountByAccountNumber(accountNumber));
	}
	
	@Override
	public Transaction createFailedTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}


	@Override
	public User getUserByUserId(int userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
	}
}
