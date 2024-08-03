package com.akshay.bankSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
