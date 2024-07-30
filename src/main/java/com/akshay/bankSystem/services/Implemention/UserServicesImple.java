package com.akshay.bankSystem.services.Implemention;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.akshay.bankSystem.repositories.UserRepository;
import com.akshay.bankSystem.services.UserServices;
import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.exceptions.ResourceNotFoundException;

@Service
public class UserServicesImple implements UserServices {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	@Override
	public User createUser(User user) {
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		System.out.println(user.toString());
		return userRepo.save(user);
	}
	
	@Override
	public User getUserById(int user_id) {
		User u =  userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("user", "Id", user_id));
		
		return u;
	}
	
	@Override
	public User getUserByUsername(String username) {
		User user = userRepo.getUserByUsername(username);
		if(user == null) {
			throw new ResourceNotFoundException("username", "username", username);
		}
		return user;
	}

	@Override
	public User updateUser(int user_id, User user) {

		User userUpdate =  userRepo.findById(user_id).
				orElseThrow(()-> new ResourceNotFoundException("user", "Id", user_id));
		
		userUpdate.setUserName(user.getUserName());
		userUpdate.setPassword(user.getPassword());
		userUpdate.setUpdatedAt(new Date());
		
		return userRepo.save(userUpdate);

	}
	
	@Override
	public boolean deleteUser(int user_id) {
			User u =  userRepo.findById(user_id).
					orElseThrow(()-> new ResourceNotFoundException("user", "Id", user_id));
			userRepo.delete(u);
			return true;
	}
	
	

}
