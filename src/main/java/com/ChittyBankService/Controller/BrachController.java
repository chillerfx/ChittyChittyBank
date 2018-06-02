package com.ChittyBankService.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ChittyBankService.Model.Account;
import com.ChittyBankService.Model.Branch;
import com.ChittyBankService.Model.Customer;
import com.ChittyBankService.Repository.AccountRepo;
import com.ChittyBankService.Repository.BankRepo;
import com.ChittyBankService.Repository.BranchRepo;
import com.ChittyBankService.Repository.CustomerRepo;
import com.ChittyBankService.Repository.EmployeeRepo;
import com.ChittyBankService.Repository.TransactionRepo;

@RestController
@RequestMapping("/branch")
public class BrachController implements ErrorController {
	

	@Autowired
	BranchRepo branchRepo;
	@Autowired
	AccountRepo accountRepo;

	@RequestMapping()
	public @ResponseBody List<Branch> branch(){
		List<Branch> branches = branchRepo.findAll();
		return branches;
	}
	@RequestMapping(value="/save", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Branch addCustomer(@RequestBody Branch branch) {
		branchRepo.save(branch);
		return branch;
	}
	@RequestMapping("/newaccount")
	public @ResponseBody Account addBranchAccount(
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
