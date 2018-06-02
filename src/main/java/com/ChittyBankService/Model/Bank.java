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

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Bank {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String	name;
	String 	headquarters;
	Date  established;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Branch> branches;
	
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
	public String getHeadquarters() {
		return headquarters;
	}
	public void setHeadquarters(String headquarters) {
		this.headquarters = headquarters;
	}
	public Date getEstablished() {
		return established;
	}
	public void setEstablished(Date established) {
		this.established = established;
	}
	public List<Branch> getBranches() {
		return branches;
	}
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
	
	@Transient
	@JsonIgnore
	public StringConverter<Branch> branchConverter = new StringConverter<Branch>() {
        @Override
        public String toString(Branch branch) {
            return branch.getName();
        }

        @Override
        public Branch fromString(String id) {
            return branches.stream()
                    .filter(item -> item.getId().equals(id))
                    .collect(Collectors.toList()).get(0);
        }
    };
	public Bank(String name, String headquarters, Date established) {
		super();
		this.name = name;
		this.headquarters = headquarters;
		this.established = established;
	}
	public Bank() {
		super();
	}
	
		
}
