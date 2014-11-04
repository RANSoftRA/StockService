package service.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.data.Stock;
import service.data.TransactionResponse;
import service.misc.YQL;
import service.persistence.TransactionDao;
import service.persistence.domain.Transaction;

@RestController
@RequestMapping("/finance")
public class FinanceController {	
	
	@Autowired
	private TransactionDao transactionDao;
	
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

	@RequestMapping(value = "/finance/stocks/{symbol}", method = RequestMethod.POST)
	@Transactional
	public TransactionResponse buyStock(@PathVariable String symbol,
			@RequestParam(value = "amount", required = true) int amount,
			@RequestParam(value = "sessionID", required = true) String sessionID) {			
		
		//TODO Current User Parameter (currently null)
		transactionDao.saveOrUpdateTransaction(new Transaction(symbol, amount, new Date(), yql.getCurrentPrice(symbol), Transaction.TransactionType.BUY, null));	
		
		return new TransactionResponse(null,null);
	}

	@RequestMapping(value = "/finance/stocks/{symbol}", method = RequestMethod.DELETE)
	@Transactional
	public TransactionResponse sellStock(@PathVariable String symbol,
			@RequestParam(value = "amount", required = true) int amount, 
			@RequestParam(value = "sessionID", required = true) String s){
		
		//TODO Current User Parameter (currently null)
		transactionDao.saveOrUpdateTransaction(new Transaction(symbol, amount, new Date(), yql.getCurrentPrice(symbol), Transaction.TransactionType.SELL, null));
		
		return new TransactionResponse(null,null);	

	}

}
