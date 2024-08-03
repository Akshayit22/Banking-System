package com.akshay.bankSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.Account;
import com.akshay.bankSystem.entities.Loan;
import com.akshay.bankSystem.entities.User;

import java.util.List;

@Repository
public interface LoanRepositoty extends JpaRepository<Loan, Integer> {
	List<Loan> findByAccount(Account account);

	List<Loan> findByUser(User user);
}
