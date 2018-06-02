package com.ChittyBankService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ChittyBankService.Model.Account;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
	//count, delete, deleteAll, deleteAll, deleteById, existsById, findById, save
		@SuppressWarnings("unchecked")
		public Account save(Account account);

		public List<Account> findByCustomer(Integer CustomerId);

		//public Account void findByCustomerDni(String string);
		
}

	