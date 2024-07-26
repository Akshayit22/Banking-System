package com.akshay.bankSystem.services.Implemention;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.akshay.bankSystem.repositories.UserRepository;
import com.akshay.bankSystem.services.UserServices;
import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.exceptions.ResourceNotFoundException;

@Service
public class UserServicesImple implements UserServices {

	@Autowired
	private UserRepository userRepo;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	@Override
	public User createUser(User user) {
		//user.setPassword(this.passwordEncoder.encode(user.getPassword()));
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
		try {
			List<User> u =  userRepo.findByUserName(username);
			if(u.isEmpty() || u.size() == 0)
				return null;
			return u.get(0);
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
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
	
	/*
	 public UserDto partialUpdate(Map<String, Object>map,Integer id) 
	{
		//List<User>list=this.userRepo.findAll();
		//List<UserDto> userDtos= list.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		//UserDto userDto=userDtos.stream().filter(l->l.getId()==id).findFirst().get();
		User user=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("user","Id",id));
		//UserDto updatedUser=this.userToDto(user);
		map.forEach((key,value)->{
			Field field=ReflectionUtils.findRequiredField(User.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field,user, value);
		});
		User updatedUser2=this.userRepo.save(user);
		UserDto userDto2=this.userToDto(updatedUser2);
		return userDto2;
	}
	 
	 */
	
	
	

}
