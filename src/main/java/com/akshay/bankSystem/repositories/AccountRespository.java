package com.akshay.bankSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.Account;


@Repository
public interface AccountRespository extends JpaRepository<Account,Integer>{
	public List<Account> findByAccountNumber(int accountNumber);

}
