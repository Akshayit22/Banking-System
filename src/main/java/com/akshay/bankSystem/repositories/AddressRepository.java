package com.akshay.bankSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akshay.bankSystem.entities.Address;
import com.akshay.bankSystem.entities.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	public List<Address> findByUser(User user);

	@Query(value = "select * from address a where user_id=(select user_id from users where username=:username) limit 1", nativeQuery = true)
	public Address getAddressByUsername(@Param("username") String username);

}
