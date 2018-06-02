package com.ChittyBankService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ChittyBankService.Model.Customer;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@SuppressWarnings("unchecked")
	public Customer save(Customer customer);

	public Customer findByDni(String string);
	
	public List<Customer> findAll();
	
}

