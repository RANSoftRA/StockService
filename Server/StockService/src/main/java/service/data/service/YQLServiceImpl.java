package service.data.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import service.data.Stock;
import service.data.StockHistory;
import service.data.jsonwrapper.YQLQueryWrapper;
import service.data.jsonwrapper.YQLQuote;
import service.misc.DateFormat;

@Component
public class YQLServiceImpl implements YQLService {

	protected final String serviceUrl = "http://query.yahooapis.com/v1/public/yql";

	@Autowired
	private DateFormat dateFormat;

	@Override
	public Stock getStock(String symbol) {	
		String uri = getYqlUri("select * from yahoo.finance.quote where symbol in ("
				+ symbol + ")");
		RestTemplate restTemplate = new RestTemplate();
		YQLQueryWrapper result = restTemplate.getForObject(uri,
				YQLQueryWrapper.class);

		if (result.getQuery().getResults().getQuote().size() != 1) {
			return null;
		}

		return new Stock(result.getQuery().getResults().getQuote().get(0));
	}

	@Override
	public List<StockHistory> getStockHistory(String symbol, String from,
			String to) {
		String uri = getYqlUri("select * "
				+ "from yahoo.finance.historicaldata " + "where symbol = \""
				+ symbol + "\" and startDate = \"" + from
				+ "\" and endDate = \"" + to + "\"");

		RestTemplate restTemplate = new RestTemplate();
		YQLQueryWrapper response = restTemplate.getForObject(uri,
				YQLQueryWrapper.class);

		List<StockHistory> stockHistories = new ArrayList<>();

		for (YQLQuote quote : response.getQuery().getResults().getQuote()) {
			stockHistories.add(new StockHistory(quote));
		}

		return stockHistories;
	}

	@Override
	public List<Stock> getAllStocks() {
		
		// Build the query
		String allSymbols = "";
		for (String symbol : symbols) {
			allSymbols += "\"" + symbol + "\",";
		}
		allSymbols = allSymbols.substring(0, allSymbols.length() - 1);
		String query = "select * from yahoo.finance.quote where symbol in ("
				+ allSymbols + ")";

		String uri = getYqlUri(query);

		// Calling the Service and getting the response
		RestTemplate restTemplate = new RestTemplate();
		YQLQueryWrapper response = restTemplate.getForObject(uri,
				YQLQueryWrapper.class);

		// Mapping the Result Quotes to Stock - Data Object
		List<Stock> stocks = new ArrayList<>();
		for (YQLQuote quote : response.getQuery().getResults().getQuote()) {
			stocks.add(new Stock(quote));
		}
		return stocks;
	}

	@Override
	public double getCurrentPrice(String symbol) {
		Stock stock = getStock(symbol);
		if(stock == null) {
			return 0.0;
		}
		return stock.getLastTradedPriceOnly();
	}

	/**
	 * Help method for creating a valid YQL URI
	 * 
	 * @param query
	 *            The Select statement
	 * @return Valid YQL Service URI
	 */
	private String getYqlUri(String query) {
		String uri = serviceUrl;
		Map<String, Object> urlVariables = new HashMap<>();
		// Adding necessary variables for the YQL
		urlVariables.put("q", query);
		urlVariables.put("format", "json");
		urlVariables.put("diagnostics", true);
		urlVariables.put("env", "store://datatables.org/alltableswithkeys");

		String prefix = "?";
		for (String key : urlVariables.keySet()) {
			uri += prefix + key + "=" + urlVariables.get(key);
			prefix = "&";
		}
		return uri;
	}

	@Override
	public boolean isSymbolSupported(String symbol) {
		for(String stockSymbol : symbols) {
			if(stockSymbol.equals(symbol)) {
				return true;
			}
		}
		return false;
	}
}
