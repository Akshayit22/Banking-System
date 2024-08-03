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
@Table(name = "nominee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nomineeId")
public class Nominee {

	@Id
	@Column(name = "nominee_id", unique = true)
	@SequenceGenerator(name = "nominee_seq", sequenceName = "nominee_seq", allocationSize = 1, initialValue = 101)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nominee_seq")
	private int nomineeId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "relation", nullable = false)
	private String relation;

	@Column(name = "mobile", nullable = false)
	private String mobile;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "createdAt", nullable = false)
	private Date createdAt = new Date();

	@Column(name = "updatedAt", nullable = false)
	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "account_number")
	@JsonBackReference
	private Account account;

	/*------- constructor - getters - setters -----------*/

	public Nominee() {
		super();
	}

	public Nominee(int nomineeId, String name, String relation, String mobile, String address, Date createdAt,
			Date updatedAt) {
		super();
		this.nomineeId = nomineeId;
		this.name = name;
		this.relation = relation;
		this.mobile = mobile;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getNomineeId() {
		return nomineeId;
	}

	public void setNomineeId(int nomineeId) {
		this.nomineeId = nomineeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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