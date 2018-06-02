package com.ChittyBankService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ChittyBankService.Model.Ticker;

import java.util.List;

@Repository
public interface TickerRepo extends JpaRepository<Ticker, Integer> {

	public Ticker save(Ticker ticker);
	List<Ticker> findAll();
}
