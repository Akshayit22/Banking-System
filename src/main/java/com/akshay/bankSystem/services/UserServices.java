package com.akshay.bankSystem.services;

import java.util.List;

import com.akshay.bankSystem.entities.User;

public interface UserServices {
	
	public List<User> getAllUsers();
	
	public User createUser(User user);
	
	public User getUserById(int user_id);
	
	public User getUserByUsername(String username);
	
	public User updateUser(int user_id,User user);
	
	public boolean deleteUser(int user_id);
	
}
