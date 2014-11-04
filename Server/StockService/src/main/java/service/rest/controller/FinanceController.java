package service.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.data.Stock;
import service.misc.YQL;

@RestController
@RequestMapping("/finance")
public class FinanceController {
	
	@Autowired
	private YQL yql;
	
	@RequestMapping(value = "/stocks", method = RequestMethod.GET)
	public List<Stock> getStocks() {
		return yql.getAllStocks();
	}
	
	@RequestMapping(value="/stocks/{symbol}/history", method=RequestMethod.GET)
	public Stock getStockHistory(@PathVariable String symbol) {
		
		return new Stock(symbol, "bla", 23243, 0.0, 0.0, 0.0, 0.0);
		
	}

}
