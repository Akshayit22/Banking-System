package com.akshay.bankSystem.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class BankUserDetails {

	@Id
	@Column(name = "user_details_id")
	@SequenceGenerator(name = "userDetails_seq", sequenceName = "userDetails_seq", allocationSize = 1, initialValue = 101)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userDetails_seq")
	private int userDetailsId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "mobile", nullable = false)
	private String mobile;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "age", columnDefinition = "int constraint age_check check(age>18)",nullable = false)
	private int age;

	@Column(name = "createdAt", nullable = false)
	private Date createdAt;

	@Column(name = "updatedAt", nullable = false)
	private Date updatedAt;

	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	private User user;

	/*------- constructor - getters - setters -----------*/

	public BankUserDetails(int userDetailsId, String name, String mobile, String gender, int age, Date createdAt,
			Date updatedAt, User user) {
		super();
		this.userDetailsId = userDetailsId;
		this.name = name;
		this.mobile = mobile;
		this.gender = gender;
		this.age = age;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
	}

	public BankUserDetails() {
		super();
	}

	public int getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(int userDetailsId) {
		this.userDetailsId = userDetailsId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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