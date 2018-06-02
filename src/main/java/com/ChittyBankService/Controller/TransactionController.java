package com.ChittyBankService.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.ChittyBankService.Model.Account;
import com.ChittyBankService.Model.Branch;
import com.ChittyBankService.Model.Transaction;

import com.ChittyBankService.Repository.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController implements ErrorController {

	@Autowired
	BranchRepo branchRepo;
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	TransactionRepo transactionRepo;
	
	@RequestMapping()
	public @ResponseBody List<Transaction> getTransactions(){
		List<Transaction> transactions = transactionRepo.findAll();
		return transactions;
	}
	
	@RequestMapping("/add")
	public @ResponseBody Account addTransaction(
			@RequestParam(value="branchid", required=true) Integer branchid) {
		
		Account account = new Account();
		// @TODO add checks and everything
		Optional<Branch> branch = branchRepo.findById(branchid);
		branch.ifPresent(b -> {
			account.setBranch(b);
			accountRepo.save(account);
		});
		return account;
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
