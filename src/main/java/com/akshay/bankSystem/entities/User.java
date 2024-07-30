package com.akshay.bankSystem.entities;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {

	@Id
	@Column(name = "user_id")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	private int userId;

	@Column(name = "username", nullable = false, unique = true)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "user_role", nullable = false)
	private String userRole;

	@Column(name = "createdAt", nullable = false)
	private Date createdAt;

	@Column(name = "updatedAt", nullable = false)
	private Date updatedAt;

	@OneToOne(cascade = CascadeType.ALL)
	private UserDetails userDetails;

	/*------- constructor - getters - setters -----------*/

	public User() {

	}

	public User(int userId, String userName, String password, String userRole, Date createdAt, Date updatedAt) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userRole = userRole;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userRole=" + userRole
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
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

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

//	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//	@JsonManagedReference
//	private List<Account> accounts = new ArrayList<>();

//	public List<Account> getAccounts() {
//		return accounts;
//	}
//
//	public void setAccounts(List<Account> accounts) {
//		this.accounts = accounts;
//	}

}
