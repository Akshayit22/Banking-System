package com.akshay.bankSystem.services.Implemention;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.akshay.bankSystem.dto.AccountDetails;
import com.akshay.bankSystem.dto.AccountDto;
import com.akshay.bankSystem.dto.LoanDto;
import com.akshay.bankSystem.dto.NomineeDto;
import com.akshay.bankSystem.entities.Account;
import com.akshay.bankSystem.entities.Loan;
import com.akshay.bankSystem.entities.Nominee;
import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.exceptions.ApiException;
import com.akshay.bankSystem.exceptions.ResourceNotFoundException;
import com.akshay.bankSystem.repositories.AccountRespository;
import com.akshay.bankSystem.repositories.LoanRepositoty;
import com.akshay.bankSystem.repositories.NomineeRepositoty;
import com.akshay.bankSystem.repositories.UserRepository;
import com.akshay.bankSystem.services.AccountServices;

@Service
public class AccountServicesImple implements AccountServices {

	@Autowired
	private AccountRespository accountRespository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NomineeRepositoty nomineeRepositoty;

	@Autowired
	private LoanRepositoty loanRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	/*----------------- Account service ---------------------*/

	@Override
	public AccountDto createAccount(String username, AccountDto account) {
		User user = this.getUserByUsername(username);

		Account newAccount = this.modelMapper.map(account, Account.class);

		newAccount.setSecurityPin(passwordEncoder.encode(account.getSecurityPin()));
		newAccount.setInterestRate(account.getAccountType().equalsIgnoreCase("saving") ? 5.5 : 3.3);
		newAccount.setUser(user);

		AccountDto newAccountDto = this.modelMapper.map(accountRespository.save(newAccount), AccountDto.class);

		return newAccountDto;
	}

	@Override
	public AccountDto updateAccount(String username, AccountDto account) {

		User user = this.getUserByUsername(username);

		Account accountUpdate = accountRespository.findById(account.getAccountNumber()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "AccountNumber", account.getAccountNumber()));

		if (!accountUpdate.getUser().getUserName().equals(username)) {
			throw new ApiException("User can only update your own accounts.");
		}

		accountUpdate.setAccountType(account.getAccountType());
		accountUpdate.setInterestRate(accountUpdate.getAccountType().equalsIgnoreCase("saving") ? 5.5 : 3.3);
		accountUpdate.setUpdatedAt(new Date());
		accountUpdate.setSecurityPin(passwordEncoder.encode(account.getSecurityPin()));
		accountUpdate.setUser(user);

		AccountDto newAccountDto = this.modelMapper.map(accountRespository.save(accountUpdate), AccountDto.class);

		return newAccountDto;

	}

	@Override
	public AccountDto getAccountByAccountNumber(String username, int accountNumber) {
		Account account = accountRespository.findById(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber));
		if (!account.getUser().getUserName().equals(username)) {
			throw new ApiException("User can only access their own accounts.");
		}
		return this.modelMapper.map(account, AccountDto.class);
	}

	@Override
	public List<AccountDto> getAccountsByUsername(String username) {

		User user = userRepository.getUserByUsername(username);

		List<Account> accountslist = accountRespository.findByUser(user);

		List<AccountDto> dtos = accountslist.stream().map((account) -> this.modelMapper.map(account, AccountDto.class))
				.collect(Collectors.toList());

		return dtos;
	}

	@Override
	public AccountDetails getAccountDetails(String username, int accountNumber) {
		Account account = accountRespository.findById(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber));
		if (!account.getUser().getUserName().equals(username)) {
			throw new ApiException("User can only access their own accounts.");
		}

		List<Loan> list = this.loanRepository.findByAccount(account);
		List<LoanDto> Loandtos = list.stream().map((loan) -> this.modelMapper.map(loan, LoanDto.class))
				.collect(Collectors.toList());

		List<Nominee> nomineeList = nomineeRepositoty.findByAccount(account);
		List<NomineeDto> nomineeDtos = nomineeList.stream()
				.map((nominee) -> this.modelMapper.map(nominee, NomineeDto.class)).collect(Collectors.toList());

		return new AccountDetails(this.modelMapper.map(account, AccountDto.class), nomineeDtos, Loandtos);
	}

	/*---------------------- Account - Nominee --------------*/

	@Override
	public NomineeDto createNominee(int accountNumber, NomineeDto nominee) {

		Account account = accountRespository.findById(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber));

		List<Nominee> list = this.nomineeRepositoty.findByAccount(account);
		if (list.size() != 0) {
			throw new ApiException("One Account can have only one Nominee For now..");
		}

		Nominee newNominee = this.modelMapper.map(nominee, Nominee.class);
		newNominee.setAccount(account);

		newNominee = nomineeRepositoty.save(newNominee);
		return this.modelMapper.map(newNominee, NomineeDto.class);
	}

	@Override
	public NomineeDto updateNominee(int nomineeId, NomineeDto nominee) {

		Nominee updateNominee = nomineeRepositoty.findById(nomineeId)
				.orElseThrow(() -> new ResourceNotFoundException("Nominee Not found", "nomineeId", nomineeId));

		updateNominee.setName(nominee.getName());
		updateNominee.setMobile(nominee.getMobile());
		updateNominee.setRelation(nominee.getRelation());
		updateNominee.setAddress(nominee.getAddress());
		updateNominee.setUpdatedAt(new Date());

		Nominee newRecord = nomineeRepositoty.save(updateNominee);

		return this.modelMapper.map(newRecord, NomineeDto.class);
	}

	/*----------------- Bank : service ---------------------*/

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accountslist = accountRespository.findAll();
		List<AccountDto> dtos = accountslist.stream().map((account) -> this.modelMapper.map(account, AccountDto.class))
				.collect(Collectors.toList());

		return dtos;
	}

	@Override
	public List<NomineeDto> getAllNominees() {
		List<Nominee> list = nomineeRepositoty.findAll();
		List<NomineeDto> dtos = list.stream().map((nominee) -> this.modelMapper.map(nominee, NomineeDto.class))
				.collect(Collectors.toList());

		return dtos;
	}

	/*-------------- Helper function ------------------------*/

	public User getUserByUsername(String username) {
		User user = userRepository.getUserByUsername(username);
		if (user == null) {
			throw new ResourceNotFoundException("username", "username", username);
		}
		return user;
	}

}
