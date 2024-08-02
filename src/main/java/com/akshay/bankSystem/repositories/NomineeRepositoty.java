package com.akshay.bankSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.Account;
import com.akshay.bankSystem.entities.Nominee;

@Repository
public interface NomineeRepositoty extends JpaRepository<Nominee,Integer>{
	public List<Nominee> findByAccount(Account account);
}

// query not working here