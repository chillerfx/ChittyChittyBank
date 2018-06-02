package com.ChittyBankService.Client.View;

import java.util.ArrayList;
import java.util.List;

import com.ChittyBankService.Client.Main;
import com.ChittyBankService.Model.Account;
import com.ChittyBankService.Model.Customer;
import com.ChittyBankService.Model.Transaction;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddAccountController {
	MainWindowController mwc;
	Customer customer = mwc.customer;
	@FXML
	TextField currencyinput;
	@FXML
	TextField typeinput;
	public void createAccount() {
		Account account = new Account();
		account.setCustomer(customer);
		account.setCurrency(currencyinput.getText());
		account.setType(typeinput.getText());
		List<Transaction> transactions = new ArrayList<Transaction>();
		account.setTransactions(transactions);
		List<Account> accounts = customer.getAccounts();
		accounts.add(account);
		customer.setAccounts(accounts);
		/* something about entity relatioship that doesn't propogate the account back to customer
		 *  so just save the customer instead
		 *  
		 */
		Main.postCustomer(customer);
		//update the main not the prettiest way
		mwc.bank = Main.restPost();
	}
}
