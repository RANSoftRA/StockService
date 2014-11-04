package service.rest.controller;

import java.util.ArrayList;
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

		List<Stock> quotes = new ArrayList<>();

		quotes.add(new Stock("YHOO", "bla", 23434, 0.23, 0.23432, 0.234, 0.252));
		quotes.add(new Stock("YHadsf", "bla", 23434, 0.23, 0.23432, 0.234,
				0.252));
		quotes.add(new Stock("asdf", "bla", 23434, 0.23, 0.23432, 0.234, 0.252));

		return quotes;

		// RestTemplate template = new RestTemplate();
		//
		// List<Quote> quotes = new ArrayList<>();
		//
		// // String stocks = YQL.stocks.toString();
		//
		// try {
		//
		// // String url = YQL.serviceUrlPrefix
		//
		// Quote quote = template.getForObject(url, Quote.class);
		// quotes.add(quote);
		// } catch (RestClientException e) {
		// e.printStackTrace();
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		//
		// return quotes;
	}

	@RequestMapping(value = "/stocks/{symbol}", method = RequestMethod.GET)
	public Stock getStock(@PathVariable String symbol) {

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
