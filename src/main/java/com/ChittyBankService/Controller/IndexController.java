package com.ChittyBankService.Controller;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ChittyBankService.Model.*;
import com.ChittyBankService.Repository.*;

@RestController
public class IndexController {
//	 private final static String ERROR_PATH = "/error";

	@Autowired
	BankRepo bankRepo;
	@Autowired
	TickerRepo tickerRepo;
	
    @RequestMapping(value="/bank", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Bank index() {

    	List<Bank> banks = bankRepo.findAll();
    	
    	if (banks.isEmpty()) {
    		// Insert dummy values
    		Date established =  Date.valueOf("2018-05-12");
    		Bank bank = new Bank("Chitty Chitty Bank", "International waters", established);

    		List<Branch> branches = new ArrayList<Branch>();
    		List<Account> branchAccounts = new ArrayList<Account>();
    		List<Account> customerAccounts = new ArrayList<Account>();
    		List<Employee> employees = new ArrayList<Employee>();
    		List<Customer> customers = new ArrayList<Customer>();
    		List<Transaction> branchTransactionsEur = new ArrayList<Transaction>();
    		List<Transaction> branchTransactionsGbp = new ArrayList<Transaction>();
    		List<Transaction> customerTransations = new ArrayList<Transaction>();
    		
    		BigDecimal ammount = new BigDecimal(1000000);
    		BigDecimal ammount1 = new BigDecimal(10000);
    		BigDecimal ammount2 = new BigDecimal(-1000);

    		Branch oilplatformkiosk = new Branch();
    		Employee employee 		= new Employee(oilplatformkiosk, "LaRevar");
    		Customer customer1 		= new Customer("--Drop Tables;", "234" , branches);
    		Account customerAccount = new Account(customer1);
    		customerAccount.setCurrency("EUR");
    		Account branchAccountEur   = new Account(oilplatformkiosk);
    		branchAccountEur.setCurrency("EUR");
    		Account branchAccountGbp   = new Account(oilplatformkiosk);
    		branchAccountGbp.setCurrency("GBP");
    		
    		Transaction branchDeposit1 = new Transaction(branchAccountEur, ammount, "USD Deposit");
    		Transaction branchDeposit2 = new Transaction(branchAccountGbp, ammount, "GBP Deposit");
    		Transaction customerDeposit1 = new Transaction(customerAccount, ammount1, "EUR Deposit");
    		Transaction customerWithdraw = new Transaction(customerAccount, ammount2, "EUR Withdraw");
    		
    		branchTransactionsEur.add(branchDeposit1);
    		branchTransactionsGbp.add(branchDeposit2);
    		customerTransations.add(customerDeposit1);
    		customerTransations.add(customerWithdraw);
    		customerAccounts.add(customerAccount);
    		branchAccounts.add(branchAccountEur);
    		branchAccounts.add(branchAccountGbp);
    		employees.add(employee);
    		customers.add(customer1);
    		
    		
    		oilplatformkiosk.setAddress("64°00'18.7\"N 0°22'46.8\"E");
    		oilplatformkiosk.setName("Oil platform-Kiosk");
    		oilplatformkiosk.setPhone_no("00987987456");
    		oilplatformkiosk.setFee(1);
    		oilplatformkiosk.setAccounts(branchAccounts);
    		oilplatformkiosk.setEmployees(employees);
    		oilplatformkiosk.setCustomers(customers);
	    	
    		customer1.setAccounts(customerAccounts);
    
     		branchAccountGbp.setTransactions(branchTransactionsGbp);
     		branchAccountEur.setTransactions(branchTransactionsEur);
    		customerAccount.setTransactions(customerTransations);
    		branches.add(oilplatformkiosk);
    	
    		bank.setBranches(branches);
    		
        	Ticker gbp = new Ticker();
        	gbp.setAsk(new BigDecimal(0.88));
        	gbp.setBid(new BigDecimal(0.87));
        	gbp.setRate(new BigDecimal(0.88));
        	gbp.setCurreny("GBP");
        	
        	
        	tickerRepo.save(gbp);
    		bankRepo.save(bank);
    		
    	}
        return banks.get(0);
        
        
    }    
}