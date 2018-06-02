package com.ChittyBankService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ChittyBankService.Model.Transaction;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
	@SuppressWarnings("unchecked")
	public Transaction save(Transaction transaction);

}

