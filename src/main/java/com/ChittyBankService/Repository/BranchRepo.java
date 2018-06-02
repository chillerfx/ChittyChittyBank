package com.ChittyBankService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ChittyBankService.Model.Branch;

import java.util.List;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Integer> {

	@SuppressWarnings("unchecked")
	public Branch save(Branch branch);

	public Branch findByName(String string);
	
}

