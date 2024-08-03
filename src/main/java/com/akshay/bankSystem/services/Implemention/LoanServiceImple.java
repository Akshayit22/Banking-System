package com.akshay.bankSystem.services.Implemention;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshay.bankSystem.configs.Constants;
import com.akshay.bankSystem.dto.LoanDto;
import com.akshay.bankSystem.entities.Account;
import com.akshay.bankSystem.entities.Loan;
import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.exceptions.ApiException;
import com.akshay.bankSystem.exceptions.ResourceNotFoundException;
import com.akshay.bankSystem.repositories.LoanRepositoty;
import com.akshay.bankSystem.services.AccountServices;
import com.akshay.bankSystem.services.LoanService;
import com.akshay.bankSystem.services.UserServices;

@Service
public class LoanServiceImple implements LoanService {

	@Autowired
	private LoanRepositoty loanRepository;

	@Autowired
	private UserServices userServices;

	@Autowired
	private AccountServices accountServices;

	@Autowired
	private ModelMapper modelMapper;

	/*--------------------- user - loan services -------------------------*/

	@Override
	public LoanDto createLoan(int accountNumber, String username, LoanDto details) {
		Loan newLoan = new Loan();
		Account account = this.modelMapper.map(accountServices.getAccountByAccountNumber(accountNumber), Account.class);

		newLoan.setUser(userServices.getUserByUsername(username));
		newLoan.setAccount(account);

		newLoan.setLoanType(details.getLoanType());
		newLoan.setLoanAmount(details.getLoanAmount());
		newLoan.setLoanStatus("Pending");

		Loan loan = loanRepository.save(newLoan);

		return this.modelMapper.map(loan, LoanDto.class);
	}

	@Override
	public LoanDto updateLoan(int accountNumber, int loanId, LoanDto details) {

		Account account = this.modelMapper.map(accountServices.getAccountByAccountNumber(accountNumber), Account.class);

		Loan loan = loanRepository.findById(loanId)
				.orElseThrow(() -> new ResourceNotFoundException("Loan Details Not Found.", "loanId", loanId));
		if (accountNumber != loan.getAccount().getAccountNumber()) {
			throw new ApiException("Loan Not Belongs to your account.");
		}
		loan.setLoanAmount(details.getLoanAmount());
		loan.setLoanType(details.getLoanType());
		loan.setUpdatedAt(new Date());

		loan = loanRepository.save(loan);

		return this.modelMapper.map(loan, LoanDto.class);
	}

	@Override
	public List<LoanDto> getLoanByUsername(String username) {
		User user = userServices.getUserByUsername(username);
		List<Loan> list = this.loanRepository.findByUser(user);

		List<LoanDto> dtos = list.stream().map((loan) -> this.modelMapper.map(loan, LoanDto.class))
				.collect(Collectors.toList());
		return dtos;
	}

	@Override
	public List<LoanDto> getLoanByAccount(int accountNumber) {
		Account account = this.modelMapper.map(accountServices.getAccountByAccountNumber(accountNumber), Account.class);

		List<Loan> list = this.loanRepository.findByAccount(account);
		List<LoanDto> dtos = list.stream().map((loan) -> this.modelMapper.map(loan, LoanDto.class))
				.collect(Collectors.toList());

		return dtos;
	}

	/*--------------------- Bank - loan services -------------------------*/

	@Override
	public List<LoanDto> getAllloans() {

		List<Loan> list = loanRepository.findAll();

		List<LoanDto> dtos = list.stream().map((loan) -> this.modelMapper.map(loan, LoanDto.class))
				.collect(Collectors.toList());

		return dtos;
	}

	@Override
	public boolean changeLoanStatus(int loanId, boolean result) {

		Loan loan = loanRepository.findById(loanId)
				.orElseThrow(() -> new ResourceNotFoundException("Loan details not found.", "Loan Id", loanId));

		loan.setLoanStatus(result ? Constants.LOAN_APPROVED : Constants.LOAN_REJECTED);

		return loanRepository.save(loan) != null;
	}

}
