package com.akshay.bankSystem.repositories;

import com.akshay.bankSystem.entities.BankUserDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<BankUserDetails, Integer>{
	
	@Query(value = "select * from user_details d where user_id=(select user_id from users where username=:username)",nativeQuery = true)
	public BankUserDetails getDetailsByUsername(@Param("username") String username);
	
}
