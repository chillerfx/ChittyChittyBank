package com.ChittyBankService.Client.View;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ChittyBankService.Client.*;
import com.ChittyBankService.Model.Account;
import com.ChittyBankService.Model.Bank;
import com.ChittyBankService.Model.Branch;
import com.ChittyBankService.Model.Customer;
import com.ChittyBankService.Model.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class MainWindowController {
	Main main;

	//Global state ... not secure or anything 
	static Bank bank;
	static Branch branch;
	static Employee employee;
	static Customer customer;

	ObservableList<Branch> branches = FXCollections.observableArrayList();
	ObservableList<Employee> employees = FXCollections.observableArrayList();
	
	/**/
	@FXML
	ComboBox<Employee> employeeCombo = new ComboBox<Employee>();
	@FXML
	ComboBox<Branch> branchCombo = new ComboBox<Branch>();

	@FXML
	Button depositbtn;
	@FXML
	Button withdrawbtn;
	@FXML
	Button swapbtn;
	@FXML
	TextField dniInput;
	

	@FXML
	public void initialize() {
		bank =  Main.restPost();
		addBranches();	
		employeeCombo.setConverter(employeeConverter);
	}

	
	@FXML
	public void selectionChanged(){
		depositbtn.setDisable(true);
		withdrawbtn.setDisable(true);
		swapbtn.setDisable(true);
	}
	/* Login window */	
	@FXML
	public void addBranches() {
		branches.addAll(bank.getBranches());
		branchCombo.setValue(branches.get(0));
		branchCombo.setItems(branches);
		branchCombo.setConverter(bank.branchConverter);
		addEmployees(branches.get(0));
	}
	@FXML
	public void addEmployees(Branch oBranch) {		
		employees.setAll(oBranch.getEmployees());
		employeeCombo.setValue(employees.get(0));
		employeeCombo.setItems(employees);
		
	}
	@FXML
	public void branchSelect() {
		
		Branch selectedBranch = branchCombo.getValue();
		branch = selectedBranch;
		employees.setAll(selectedBranch.getEmployees());
		employeeCombo.setValue(employees.get(0));
		employeeCombo.setItems(employees);
	}
	@FXML
	public void employeeSelect() {
		Employee selectedEmployee = employeeCombo.getValue();
		employee = selectedEmployee;
	}
	@FXML
	public void login() throws IOException {
		branch = branchCombo.getValue();
		employee = employeeCombo.getValue();
		Main.loadView("View/MainView.fxml");
	}
	/*Main window*/
	@FXML
	public void addCustomer() throws IOException {
		Main.showDialog("View/AddCustomer.fxml", "Create New Customer");
	}
	//@FXML
	public void depositBtn() throws IOException {
		Main.showDialog("View/Deposit.fxml", "Deposit to Customers Account");
		System.out.println(customer.getAccounts().get(0).getCurrency());	
		
	}
	//@FXML
	public void whitdrawBtn() throws IOException {
		Main.showDialog("View/Whithdraw.fxml", "Whithdraw Funds");
	}
	@FXML
	public void swapBtn() throws IOException {
		Main.showDialog("View/Swap.fxml", "Swap currency");
	}
	@FXML
	public void accountBtn() throws IOException {
		Main.showDialog("View/AddNewAccount.fxml", "Add new account");
	}

	@FXML void findCustomerBtn() throws IOException {
		
		String dni = dniInput.getText();
		List<Customer> customers = branch.getCustomers();
		List<Customer> c1 = customers.stream()
						.filter(item -> item.getDni().equals(dni))
						.collect(Collectors.toList());
		if (c1.size() != 1) {
			depositbtn.setDisable(true);
			withdrawbtn.setDisable(true);
			swapbtn.setDisable(true);
			//Main.showDialog("View/NotFound.fxml", "Customer Not found");
		} else {
			Customer c = c1.get(0);
			depositbtn.setDisable(false);
			withdrawbtn.setDisable(false);
			swapbtn.setDisable(false);
			customer = c;
		}
	}
 
	StringConverter<Employee> employeeConverter = new StringConverter<Employee>() {
        @Override
        public String toString(Employee employee) {
            return employee.getName();
        }

        @Override
        public Employee fromString(String id) {
            return employees.stream()
                    .filter(item -> item.getId().equals(id))
                    .collect(Collectors.toList()).get(0);
        }
    };
}

