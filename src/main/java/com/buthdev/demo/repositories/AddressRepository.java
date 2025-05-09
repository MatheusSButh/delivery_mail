package com.buthdev.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buthdev.demo.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
	
}