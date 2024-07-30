package com.akshay.bankSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.Loan;

@Repository
public interface LoanRepositoty extends JpaRepository<Loan, Integer> {

}
