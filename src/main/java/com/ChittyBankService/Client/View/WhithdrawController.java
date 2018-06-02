package com.ChittyBankService.Client.View;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.ChittyBankService.Client.Main;
import com.ChittyBankService.Model.Account;
import com.ChittyBankService.Model.Customer;
import com.ChittyBankService.Model.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class WhithdrawController {
	MainWindowController mwc;
	ObservableList<Account> accounts = FXCollections.observableArrayList();
	Customer customer = mwc.customer;
	
	Account account;

	@FXML
	ComboBox<Account> accountCombo = new ComboBox<Account>();
	@FXML
	Label balanceLabel;
	@FXML
	TextField wInput;
	@FXML
	public void initialize() {
		// I don't have time to think on how to do this in proper nice way
		// defaults to first account 
		account = customer.getAccounts().get(0);
		accounts.setAll(customer.getAccounts());
		accountCombo.setValue(customer.getAccounts().get(0));
		accountCombo.setItems(accounts);
		balanceLabel.setText(customer.getAccounts().get(0).getBalance().toString());
		accountCombo.setConverter(accountConverter);
	}
	@FXML
	public void selectAccount() {
		account = accountCombo.getValue();
		balanceLabel.setText(account.getBalance().toString());
	}
	@FXML 
	public void wCustomerBtn() {
		BigDecimal amount = new BigDecimal("-"+wInput.getText());
		
		List<Transaction> transations = account.getTransactions();
		Transaction transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setAmmount(amount);
		transaction.setDetails("Withdraw");
		transations.add(transaction);
		account.setTransactions(transations);
		Main.postTransaction(account);
	}
    StringConverter<Account> accountConverter = new StringConverter<Account>() {
        @Override
        public String toString(Account account) {
        	return account.getId() + " " +  account.getCurrency();
        }

        @Override
        public Account fromString(String id) {
            return accounts.stream()
                    .filter(item -> item.getId().equals(id))
                    .collect(Collectors.toList()).get(0);
        }
    };
}
