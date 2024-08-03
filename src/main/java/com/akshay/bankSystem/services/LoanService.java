package com.akshay.bankSystem.services;

import java.util.List;

import com.akshay.bankSystem.dto.LoanDto;

public interface LoanService {

	public LoanDto createLoan(int accountNumber, String username, LoanDto details);

	public LoanDto updateLoan(int accountNumber, int loanId, LoanDto details);

	public List<LoanDto> getLoanByUsername(String username);

	public List<LoanDto> getLoanByAccount(int accountNumber);

	public List<LoanDto> getAllloans();

	public boolean changeLoanStatus(int loanId, boolean result);

}
