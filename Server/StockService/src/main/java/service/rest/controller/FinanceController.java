package service.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.data.Stock;

@RestController
@RequestMapping("/finance")
public class FinanceController {
	
	@RequestMapping(value = "/stocks", method = RequestMethod.GET)
	public List<Stock> getStocks() {

		
		
		List<Stock> quotes = new ArrayList<>();
		
		quotes.add(new Stock("YHOO", "bla", 23434, 0.23, 0.23432, 0.234, 0.252));
		quotes.add(new Stock("YHadsf", "bla", 23434, 0.23, 0.23432, 0.234, 0.252));
		quotes.add(new Stock("asdf", "bla", 23434, 0.23, 0.23432, 0.234, 0.252));
		
		return quotes;
		
//		RestTemplate template = new RestTemplate();
//
//		List<Quote> quotes = new ArrayList<>();
//		
////		String stocks = YQL.stocks.toString();
//		
//		try {
//			
////			String url = YQL.serviceUrlPrefix 
//			
//			Quote quote = template.getForObject(url, Quote.class);
//			quotes.add(quote);
//		} catch (RestClientException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		return quotes;
	}
	
	@RequestMapping(value="/stocks/{symbol}", method=RequestMethod.GET)
	public Stock getStock(@PathVariable String symbol) {
		
		return new Stock(symbol, "bla", 23243, 0.0, 0.0, 0.0, 0.0);
		
	}

}
