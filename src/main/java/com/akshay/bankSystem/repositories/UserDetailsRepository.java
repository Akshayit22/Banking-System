package com.akshay.bankSystem.repositories;

import com.akshay.bankSystem.entities.BankUserDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<BankUserDetails, Integer>{

}
