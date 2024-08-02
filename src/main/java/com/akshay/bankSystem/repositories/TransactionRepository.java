package com.akshay.bankSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.Account;
import com.akshay.bankSystem.entities.Transaction;


@Repository
@EnableJpaRepositories
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

	public List<Transaction> findByAccount(Account account);
	
//	@Query("select t from Transaction t join Account a on a.accountNumber=:accountNumber")
//	public List<Transaction> getTransactionByAccountNumber(@Param("accountNumber")int accountNumber);
	
}

