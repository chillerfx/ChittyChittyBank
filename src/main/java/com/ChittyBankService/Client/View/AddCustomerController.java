package com.ChittyBankService.Client.View;

import java.util.ArrayList;
import java.util.List;

import com.ChittyBankService.Client.Main;
import com.ChittyBankService.Model.Branch;
import com.ChittyBankService.Model.Customer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddCustomerController {
	MainWindowController mwc;
	Branch branch = mwc.branch;
	@FXML
	TextField dni;
	@FXML
	TextField name;
	
	public void addCustomer() {
		Customer customer = new Customer();
		customer.setDni(dni.getText());
		customer.setName(name.getText());

		List<Customer> customers = branch.getCustomers();
		customers.add(customer);
		branch.setCustomers(customers);
		Main.postBranch(branch);
		mwc.bank = Main.restPost();
	}
}
