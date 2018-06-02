package com.ChittyBankService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ChittyBankService.Model.Ticker;
import com.ChittyBankService.Repository.TickerRepo;


@RestController
@RequestMapping(value="/ticker")
public class TickerController {
	
	@Autowired
	TickerRepo tickerRepo;
	
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Ticker> getTickers(){
		List<Ticker> tickers = tickerRepo.findAll();
		return tickers;
	}
}
