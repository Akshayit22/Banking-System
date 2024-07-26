package com.akshay.bankSystem.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;


@Entity
@Table(name="transactions")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "transactionId")
public class Transaction {
	
	@Id
	@Column(name ="transaction_id",unique=true)
	@SequenceGenerator(name = "trans_seq", sequenceName= "trans_seq", allocationSize = 1,initialValue=1000)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="trans_seq")
	private int transactionId;
	
	@Column(name ="transaction_type",nullable = false)
	private String transactionType;
	
	@Column(name ="transaction_status",nullable = false)
	private String status;
	
	@Column(name ="transaction_amount",nullable = false)
	private int amount;
	
	@Column(name ="time_stamp",nullable = false)
	private Date createdAt = new Date();
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="account_number")
	private Account account;
	
	public Transaction() {
		
	}


	public Transaction(int transactionId, String transactionType, String status, int amount, Date createdAt) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.status = status;
		this.amount = amount;
		this.createdAt = createdAt;
	}


	public int getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}
	
	@PrePersist
	void preInsert() {
		if(this.createdAt == null) {
			this.createdAt = new Date();
		}
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionType=" + transactionType + ", status="
				+ status + ", amount=" + amount + ", createdAt=" + createdAt + "]";
	}

	
	
	
	
	
	
}
