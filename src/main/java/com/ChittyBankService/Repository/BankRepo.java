package com.ChittyBankService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ChittyBankService.Model.Bank;

import java.util.List;
import java.util.Set;

@Repository
public interface BankRepo extends JpaRepository<Bank, Integer> {
	//count, delete, deleteAll, deleteAll, deleteById, existsById, findById, save
	@SuppressWarnings("unchecked")
	public Bank save(Bank bank);
	List<Bank> findAll();
	public Bank findByName(String string);
	
}

