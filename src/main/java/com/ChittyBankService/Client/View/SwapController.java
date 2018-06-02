package com.ChittyBankService.Client.View;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Transient;

import com.ChittyBankService.Client.Main;
import com.ChittyBankService.Model.Account;
import com.ChittyBankService.Model.Branch;
import com.ChittyBankService.Model.Customer;
import com.ChittyBankService.Model.Ticker;
import com.ChittyBankService.Model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class SwapController {
	MainWindowController mwc;
	ObservableList<Account> accounts = FXCollections.observableArrayList();
	Customer customer = mwc.customer;
	Branch branch = mwc.branch;
	
	List<Account> branchAccounts;
	List<Ticker> tickers;
	Ticker ticker;
	Account account;
	Account fromBranchAccount;
	Account toBranchAccount;
	Integer fee;
	BigDecimal a; //amount in base currency
	BigDecimal t; //total as in *to currency*
	String fromCurrency;
	String toCurrency;
	
	@FXML 
	Label fromamount;
	@FXML 
	Label toamount;
	@FXML 
	Label branchfee;
	@FXML 
	Label total;
	@FXML
	ComboBox<Account> fromcombo;
	@FXML
	ComboBox<Account> tocombo;
	@FXML
	TextField amountinput;
	
	public void initialize() {
		branchAccounts = branch.getAccounts();
		account = customer.getAccounts().get(0);
		accounts.setAll(customer.getAccounts());
		fromcombo.setValue(customer.getAccounts().get(0));
		fromcombo.setItems(accounts);
		
		tocombo.setValue(customer.getAccounts().get(0));
		tocombo.setItems(accounts);
		tocombo.setConverter(customer.accountConverter);
		fromcombo.setConverter(customer.accountConverter);
		
		fee = branch.getFee();
		branchfee.setText(fee + " %");
		
		comboBalance();
		comboBalance1();
		tickers = Arrays.asList(Main.getTickers());

	}
	@FXML
	public void updateTotal() {

		String amount = amountinput.getText();
		a = new BigDecimal(amount);
		fromCurrency = fromcombo.getValue().getCurrency().toString();
		toCurrency = tocombo.getValue().getCurrency().toString();
		ticker = tickers.stream()
                .filter(item -> item.getCurreny().equals(toCurrency))
                .collect(Collectors.toList()).get(0);
		t = (a.multiply(ticker.getBid())).subtract((a.multiply(new BigDecimal(fee).divide(new BigDecimal(100)))));
		total.setText(t.toString());		
	}
	public void swapBtn() {
		
		Account customerFromAccount = fromcombo.getValue();
		Account toAccount = tocombo.getValue();
		
		List<Transaction> from = customerFromAccount.getTransactions();
		Transaction transactionFromCustomerAccount = new Transaction(); // ---
		transactionFromCustomerAccount.setAccount(customerFromAccount);
		transactionFromCustomerAccount.setAmmount(a.negate());
		transactionFromCustomerAccount.setDetails("currency SWAP ");
		from.add(transactionFromCustomerAccount);
		customerFromAccount.setTransactions(from);
		
		List<Transaction> to = toAccount.getTransactions();
		Transaction transactionToCustomersAccount = new Transaction();
		transactionToCustomersAccount.setAccount(toAccount);
		transactionToCustomersAccount.setAmmount(t);
		transactionToCustomersAccount.setDetails("currency SWAP ");
		to.add(transactionToCustomersAccount);
		toAccount.setTransactions(to);
		
		fromBranchAccount = branchAccounts.stream()
				                .filter(item -> item.getCurrency().equals(toCurrency))
				                .collect(Collectors.toList()).get(0);
		List<Transaction> fromBranch =  fromBranchAccount.getTransactions();
		Transaction transactionFromBranchAccount = new Transaction();
		transactionFromBranchAccount.setAccount(fromBranchAccount);
		transactionFromBranchAccount.setAmmount(t);
		transactionFromBranchAccount.setDetails("currency SWAP ");
		fromBranch.add(transactionFromBranchAccount);
		fromBranchAccount.setTransactions(fromBranch);
		
		toBranchAccount = branchAccounts.stream()
				                .filter(item -> item.getCurrency().equals(fromCurrency))
				                .collect(Collectors.toList()).get(0);
		
		List<Transaction> toBranch =  toBranchAccount.getTransactions();
		Transaction transactionToBranchAccount = new Transaction();
		transactionToBranchAccount.setAccount(toBranchAccount);
		transactionToBranchAccount.setAmmount(a);
		transactionToBranchAccount.setDetails("currency SWAP ");
		toBranch.add(transactionToBranchAccount);
		toBranchAccount.setTransactions(toBranch);
		
		Main.postTransaction(customerFromAccount);
		Main.postTransaction(toAccount);
		Main.postTransaction(fromBranchAccount);
		Main.postTransaction(toBranchAccount);

	}
	@FXML
	public void comboBalance() {
		String amount = fromcombo.getValue().getBalance().toString();
		fromamount.setText(amount);
		
	}
	@FXML
	public void comboBalance1() {
		String amount = tocombo.getValue().getBalance().toString();
		toamount.setText(amount);
	}

};	
