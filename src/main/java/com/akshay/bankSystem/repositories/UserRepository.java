package com.akshay.bankSystem.repositories;

import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{	
//	@Query("select user u from user u where u.username=?1")
	public List<User> findByUserName(String username);
}
