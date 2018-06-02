package com.ChittyBankService.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javafx.util.StringConverter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Branch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer Id;
	String name;
	String address;
	String phone_no;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Account> accounts;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Employee> employees;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Customer> customers;
	Integer fee;
	Date createdat;
	
/*	@Transient
	@JsonIgnore
	public StringConverter<Employee> employeeConverter = new StringConverter<Employee>() {
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
    };*/
	
	public Branch(String name, String address, String phone_no, List<Account> accounts, List<Employee> employees,
			List<Customer> customers, Integer fee) {
		super();
		this.name = name;
		this.address = address;
		this.phone_no = phone_no;
		this.accounts = accounts;
		this.employees = employees;
		this.customers = customers;
		this.fee = fee;
	}

	public Branch() {
		super();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Date getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}

	
		
}
