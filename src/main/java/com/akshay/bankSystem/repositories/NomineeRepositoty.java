package com.akshay.bankSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.Nominee;

@Repository
public interface NomineeRepositoty extends JpaRepository<Nominee,Integer>{

}
