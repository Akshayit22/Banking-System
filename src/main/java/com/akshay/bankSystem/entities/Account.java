package com.akshay.bankSystem.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "accountNumber")
public class Account {

	@Id
	@Column(name = "account_number", unique = true)
	@SequenceGenerator(name = "acc_seq", sequenceName = "acc_seq", allocationSize = 1, initialValue = 111001)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq")
	private int accountNumber;

	@Column(name = "account_type", nullable = false)
	private String accountType;

	@Column(name = "balance", nullable = false)
	private int balance;

	@Column(name = "security_pin", nullable = false)
	private String securityPin;

	@Column(name = "interest_rate", nullable = false)
	private double interestRate;

	@Column(name = "createdAt", nullable = false)
	private Date createdAt = new Date();

	@Column(name = "updatedAt", nullable = false)
	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	/*------- constructor - getters - setters -----------*/

	public Account() {
		super();
	}

	public Account(int accountNumber, String accountType, int balance, String securityPin, double interestRate,
			Date createdAt, Date updatedAt) {
		super();
		this.accountNumber = accountNumber;
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
		if (this.createdAt == null) {
			this.createdAt = new Date();
		}
		if (this.updatedAt == null) {
			this.updatedAt = new Date();
		}
	}

}

//@OneToMany(mappedBy = "account",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//@JsonManagedReference
//private List<Transaction> transactions = new ArrayList<>();
