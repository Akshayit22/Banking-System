package com.akshay.bankSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.Transaction;


@Repository
@EnableJpaRepositories
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

}

// while query use class name and field names as table and column names