package com.ChittyBankService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ChittyBankService.Model.Account;
import com.ChittyBankService.Model.Customer;
import com.ChittyBankService.Repository.AccountRepo;
import com.ChittyBankService.Repository.BankRepo;
import com.ChittyBankService.Repository.BranchRepo;
import com.ChittyBankService.Repository.CustomerRepo;
import com.ChittyBankService.Repository.EmployeeRepo;
import com.ChittyBankService.Repository.TransactionRepo;

@RestController
@RequestMapping("/customer")
public class CustomerController implements ErrorController {
	
	@Autowired
	BankRepo bankRepo;
	@Autowired
	BranchRepo branchRepo;
	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	TransactionRepo transactionRepo;
	
	@RequestMapping()
	public @ResponseBody List<Customer> getCustomers(){
		List<Customer> customers = customerRepo.findAll();
		return customers;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Customer addCustomer(@RequestBody Customer customer) {
		customerRepo.save(customer);
		return customer;
	}
	@RequestMapping("/find/{dni}")
	public @ResponseBody Customer getCustomer(@PathVariable String dni){
		Customer customer = customerRepo.findByDni(dni);
		return customer;
	}
	
	@RequestMapping("/newaccount")
	public @ResponseBody Account addCustomerAccount(
			@RequestParam(value="dni", required=true) String dni) {
		
		// @TODO add checks and everything
		Customer customer = customerRepo.findByDni(dni);
		Account account = new Account(customer);
		
		accountRepo.save(account); 	
		return account;
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
