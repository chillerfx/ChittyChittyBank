package com.ChittyBankService.Model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javafx.util.StringConverter;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String name;
	
	@Column(unique=true)
	String dni;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Account> accounts;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Branch> branches;
	
	@Transient
	@JsonIgnore
    public StringConverter<Account> accountConverter = new StringConverter<Account>() {
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
	
	public Customer(String name, String dni, List<Branch> branches) {
		super();
		this.name = name;
		this.dni = dni;
		this.branches = branches;
	}

	public Customer() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

}
