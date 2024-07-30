package com.akshay.bankSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.bankSystem.entities.User;
import com.akshay.bankSystem.services.UserServices;

@RestController
public class UserController {

	@Autowired
	private UserServices service;

	@GetMapping("/bank/users")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> all = service.getAllUsers();
		if(all == null || all.size() == 0) {
			return ResponseEntity.status(500).body(null);
		}
		return ResponseEntity.status(200).body(all);
	}
	
	@GetMapping("/user/id/{user_id}")
	public ResponseEntity<User> userByUserId(@PathVariable int user_id) {
		User u = service.getUserById(user_id);
		if(u == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(u);
	}
	
	@GetMapping("/user/username/{username}")
	public ResponseEntity<User> userByUserId(@PathVariable String username) {
		User u = service.getUserByUsername(username);
		if(u == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(u);
	}
	
	@PutMapping("/user/{user_id}")
	public ResponseEntity<User> updateUser(@PathVariable int user_id,@RequestBody User user){
		User u = service.updateUser(user_id, user);
		if (u == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
	@DeleteMapping("/admin/user/{user_id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public HttpStatus deleteUser(@PathVariable int user_id) {
		if(service.deleteUser(user_id)) {
			return HttpStatus.ACCEPTED;
		}
		return HttpStatus.NOT_MODIFIED;
	}
	
	@PostMapping("/bank/user")
	@PreAuthorize("hasAuthority('EMPLOYEE','ADMIN')")
	public ResponseEntity<User> creatUser(@RequestBody User user) {
		User u = service.createUser(user);
		if (u == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
}
