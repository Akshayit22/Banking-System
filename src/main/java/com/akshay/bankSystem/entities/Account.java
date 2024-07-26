package com.akshay.bankSystem.entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name="accounts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "accountNumber")
public class Account {
	
	@Id
	@Column(name="account_number",unique = true)
	@SequenceGenerator(name = "acc_seq", sequenceName= "acc_seq", allocationSize = 1,initialValue=111000)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="acc_seq")
	private int accountNumber;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="mobile",nullable=false)
	private String mobile;
	
	@Column(name="account_type",nullable=false)
	private String accountType;
	
	@Column(name="balance",nullable=false)
	private int balance;
	
	@Column(name="security_pin",nullable=false)
	private String securityPin;
	
	@Column(name="interest_rate",nullable=false)
	private double interestRate;
	
	@Column(name="createdAt",nullable=false)
	private Date createdAt= new Date();
	
	@Column(name="updatedAt",nullable=false)
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy = "account",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonManagedReference
	private List<Transaction> transactions = new ArrayList<>();
	
	
	
	public Account() {
		super();
	}

	public Account(int accountNumber, String name, String mobile, String accountType, int balance, String securityPin,
			double interestRate, Date createdAt, Date updatedAt) {
		super();
		this.accountNumber = accountNumber;
		this.name = name;
		this.mobile = mobile;
		this.accountType = accountType;
		this.balance = balance;
		this.securityPin = securityPin;
		this.interestRate = interestRate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getSecurityPin() {
		return securityPin;
	}

	public void setSecurityPin(String securityPin) {
		this.securityPin = securityPin;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@PrePersist
	void preInsert() {
		if(this.createdAt == null) {
			this.createdAt = new Date();
		}
		if(this.updatedAt == null) {
			this.updatedAt = new Date();
		}
	}
	
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", name=" + name + ", mobile=" + mobile + ", accountType="
				+ accountType + ", balance=" + balance + ", securityPin=" + securityPin + ", interestRate="
				+ interestRate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
