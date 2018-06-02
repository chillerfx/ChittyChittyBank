package com.ChittyBankService.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.util.stream.Collectors;


@Entity
public class Ticker {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String curreny;
	BigDecimal rate;
	BigDecimal ask;
	BigDecimal bid;
	
	
	public Ticker(String curreny, BigDecimal rate) {
		super();
		this.curreny = curreny;
		this.rate = rate;
	}

	public Ticker() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurreny() {
		return curreny;
	}

	public void setCurreny(String curreny) {
		this.curreny = curreny;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getAsk() {
		return ask;
	}

	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}
	
}
 