package com.akshay.bankSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.Nominee;

@Repository
public interface NomineeRepositoty extends JpaRepository<Nominee,Integer>{
	@Query("select n from Nominee n join Account a on a.accountNumber=:accountNumber")
	public Nominee getNomineeByAccountNumber(@Param("accountNumber") int accountNumber);
}
