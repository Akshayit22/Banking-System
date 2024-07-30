package com.akshay.bankSystem.services.Implemention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akshay.bankSystem.configs.Constants;
import com.akshay.bankSystem.entities.Account;
import com.akshay.bankSystem.entities.Transaction;
import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.exceptions.ApiException;
import com.akshay.bankSystem.exceptions.ResourceNotFoundException;
import com.akshay.bankSystem.exceptions.TransactionException;
import com.akshay.bankSystem.payloads.TransactionPayload;
import com.akshay.bankSystem.repositories.AccountRespository;
import com.akshay.bankSystem.repositories.TransactionRepository;
import com.akshay.bankSystem.repositories.UserRepository;
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

	@Override
	@Transactional
	public Transaction depositeMoney(TransactionPayload details) throws TransactionException{
		
//		if(details.getAmount() < 0) {
//			throw new ApiException("Enter Valide Amount");
//		}
//		
//		Transaction transaction = new Transaction();
//		transaction.setAmount(details.getAmount());
//		transaction.setStatus(Constants.STATUS_PENDING);
//		transaction.setTransactionType(Constants.DEPOSIT_TRANSACTION);
//
//			this.getUserByUserId(details.getUserId());
//
//			Account account = this.getAccountByAccount(details.getAccountNumber());
//
//			List<Transaction> alltransList = account.getTransactions();
//
//			account.setBalance(details.getAmount() + account.getBalance());
//			transaction.setStatus(Constants.STATUS_SUCCESS);
//
//			alltransList.add(transaction);
//			account.setTransactions(alltransList);
//			transaction.setAccount(account);
//
//			Account updated = accountRespository.save(account);
//
//			return updated.getTransactions().get(updated.getTransactions().size() - 1);
		return null;
	}

	@Override
	@Transactional
	public Transaction WithdrawMoney(TransactionPayload details) throws TransactionException{
		
//		if(details.getAmount() < 0) {
//			throw new ApiException("Enter Valide amount");
//		}
//
//		Transaction transaction = new Transaction();
//		transaction.setAmount(details.getAmount());
//		transaction.setStatus(Constants.STATUS_PENDING);
//		transaction.setTransactionType(Constants.WITHDRAW_TRANSACTION);
//
//			this.getUserByUserId(details.getUserId());
//
//			Account account = this.getAccountByAccount(details.getAccountNumber());
//
//			transaction.setAccount(account);
//
//			if (account.getBalance() < details.getAmount()) { // insufisiant balance
//				throw new TransactionException( "Insufisiant balance",transaction,details.getAccountNumber());
//			}
//
//			if (account.getAccountType().equalsIgnoreCase("Saving") && Constants.SAVING_MIN_BALANCE > (account.getBalance() - details.getAmount())) {
//				
//				throw new TransactionException( "Minimum balance of " + Constants.SAVING_MIN_BALANCE + " should maintained in saving Account.",transaction,details.getAccountNumber());
//			}
//
//			account.setBalance(account.getBalance() - details.getAmount());
//			transaction.setStatus(Constants.STATUS_SUCCESS);
//
//			List<Transaction> alltransList = account.getTransactions();
//			alltransList.add(transaction);
//			account.setTransactions(alltransList);
//
//			Account updated = accountRespository.save(account);
//
//			return updated.getTransactions().get(updated.getTransactions().size() - 1);
		return null;
	}

	@Override
	@Transactional
	public Transaction TransferMoney(TransactionPayload details) {
		
//		if(details.getAmount() < 0) {
//			throw new ApiException("Enter Valide amount");
//		}
//
//		Transaction transaction = new Transaction();
//		transaction.setAmount(details.getAmount());
//		transaction.setStatus(Constants.STATUS_PENDING);
//		transaction.setTransactionType(Constants.MONEY_TRANSFER);
//
//			this.getUserByUserId(details.getUserId());
//
//			Account account = this.getAccountByAccount(details.getAccountNumber());
//
//			Account targetAccount = this.getAccountByAccount(details.getTargetAccountNumber());
//
//			List<Transaction> alltransList = account.getTransactions();
//			List<Transaction> alltragetTransList = targetAccount.getTransactions();
//
//			transaction.setAccount(account);
//
//			if (account.getBalance() < details.getAmount()) { // Insufficient balance
//				throw new TransactionException( "Insufisiant balance",transaction,details.getAccountNumber());
//			}
//
//			if (account.getAccountType().equalsIgnoreCase("Saving") && Constants.SAVING_MIN_BALANCE > (account.getBalance() - details.getAmount())) {
//				
//				throw new TransactionException( "Minimum balance of " + Constants.SAVING_MIN_BALANCE + " should main in saving Account.",transaction,details.getAccountNumber());
//
//			}
//
//			account.setBalance(account.getBalance() - details.getAmount());
//			transaction.setStatus(Constants.STATUS_SUCCESS);
//			alltransList.add(transaction);
//			account.setTransactions(alltransList);
//
//			Account updated = accountRespository.save(account);
//			
//			Transaction receviedTransaction = new Transaction();
//			receviedTransaction.setAmount(details.getAmount());
//			receviedTransaction.setStatus(Constants.STATUS_SUCCESS);
//			receviedTransaction.setTransactionType(Constants.MONEY_RECEIVED + details.getAccountNumber());
//
//			targetAccount.setBalance(targetAccount.getBalance() + details.getAmount());
//			receviedTransaction.setAccount(targetAccount);
//			alltransList.add(receviedTransaction);
//			targetAccount.setTransactions(alltragetTransList);
//
//			accountRespository.save(targetAccount);
//
//			return updated.getTransactions().get(updated.getTransactions().size() - 1);
		return null;
	}

	@Override
	public List<Transaction> getAllTransactions(){
		return transactionRepository.findAll();
	}
	
	@Override
	public List<Transaction> getAccountTransactions(int accountNumber){
		//return this.getAccountByAccount(accountNumber).getTransactions();
		return null;
	}
	
	@Override
	public Transaction createFailedTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	
	@Override
	public Account getAccountByAccount(int accountNumber) {
		List<Account> accounts = accountRespository.findByAccountNumber(accountNumber);
		if (accounts.isEmpty() || accounts.get(0) == null) {
			throw new ResourceNotFoundException("accountNumber", "accountNumber", accountNumber);
		}
		return accounts.get(0);
	}

	@Override
	public User getUserByUserId(int userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
	}
}
