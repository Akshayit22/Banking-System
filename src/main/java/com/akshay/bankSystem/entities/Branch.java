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
@Table(name = "branch")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "branchId")
public class Branch {

	@Id
	@Column(name = "branch_id", unique = true)
	@SequenceGenerator(name = "branch_seq", sequenceName = "branch_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_seq")
	private int branchId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "ifsc_code", nullable = false)
	private String ifscCode;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "pincode", nullable = false)
	private int pincode;

	@Column(name = "createdAt", nullable = false)
	private Date createdAt = new Date();

	@Column(name = "updatedAt", nullable = false)
	private Date updatedAt;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "account_number")
	private Account account;

	/*------- constructor - getters - setters -----------*/

	public Branch() {
		super();
	}

	public Branch(int branchId, String name, String ifscCode, String address, int pincode, Date createdAt,
			Date updatedAt, Account account) {
		super();
		this.branchId = branchId;
		this.name = name;
		this.ifscCode = ifscCode;
		this.address = address;
		this.pincode = pincode;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.account = account;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
