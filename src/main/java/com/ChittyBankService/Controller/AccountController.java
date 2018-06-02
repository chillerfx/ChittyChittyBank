package com.ChittyBankService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ChittyBankService.Model.Account;
import com.ChittyBankService.Model.Customer;
import com.ChittyBankService.Repository.AccountRepo;

@RestController
@RequestMapping("/account")
public class AccountController implements ErrorController {
	
	@Autowired
	AccountRepo accountRepo;
	
	@RequestMapping()
	public @ResponseBody List<Account> getAccounts(){
		List<Account> accounts = accountRepo.findAll();
		return accounts;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void saveAccount(@RequestBody Account account ) {
		accountRepo.save(account);
	}
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
