package com.akshay.bankSystem.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;

@Data
public class AccountDetails {

	@Schema(accessMode = AccessMode.READ_ONLY)
	private AccountDto account;

	@Schema(accessMode = AccessMode.READ_ONLY)
	private List<NomineeDto> nominee;

	@Schema(accessMode = AccessMode.READ_ONLY)
	private List<LoanDto> loan;

	public AccountDetails(AccountDto account, List<NomineeDto> nominee, List<LoanDto> loan) {
		super();
		this.account = account;
		this.nominee = nominee;
		this.loan = loan;
	}

	public AccountDetails() {
		super();
	}

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}

	public List<NomineeDto> getNominee() {
		return nominee;
	}

	public void setNominee(List<NomineeDto> nominee) {
		this.nominee = nominee;
	}

	public List<LoanDto> getLoan() {
		return loan;
	}

	public void setLoan(List<LoanDto> loan) {
		this.loan = loan;
	}

}
