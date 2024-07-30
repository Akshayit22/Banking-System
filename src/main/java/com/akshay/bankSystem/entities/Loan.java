package com.akshay.bankSystem.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "loanId")
public class Loan {

	@Id
	@Column(name = "loan_id", unique = true)
	@SequenceGenerator(name = "loan_seq", sequenceName = "loan_seq", allocationSize = 1, initialValue = 1001)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_seq")
	private int LoanId;

	@Column(name = "loan_amount", nullable = false)
	private int loanAmount;

	@Column(name = "loan_type", nullable = false)
	private String loanType;

	@Column(name = "loan_status", nullable = false)
	private String loanStatus;

	@Column(name = "createdAt", nullable = false)
	private Date createdAt = new Date();

	@Column(name = "updatedAt", nullable = false)
	private Date updatedAt;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "account_number")
	private Account account;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "branch_id")
	private Branch branch;

	/*------- constructor - getters - setters -----------*/

	public Loan() {
		super();
	}

	public Loan(int loanId, int loanAmount, String loanType, String loanStatus, Account account, User user,
			Branch branch) {
		super();
		LoanId = loanId;
		this.loanAmount = loanAmount;
		this.loanType = loanType;
		this.loanStatus = loanStatus;
		this.account = account;
		this.user = user;
		this.branch = branch;
	}

	public int getLoanId() {
		return LoanId;
	}

	public void setLoanId(int loanId) {
		LoanId = loanId;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@PrePersist
	void preInsert() {
		if (this.createdAt == null) {
			this.createdAt = new Date();
		}
		if (this.updatedAt == null) {
			this.updatedAt = new Date();
		}
	}
}
