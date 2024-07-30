package com.akshay.bankSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
