package service.misc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import service.data.Stock;
import service.data.StockHistory;
import service.data.YQLQuery;

@Component
public class YQL {

	public final String serviceUrl = "https://query.yahooapis.com/v1/public/yql";
	public final String[] symbols = new String[] { "YHOO", "AAPL", "GOOG",
			"MSFT", "AGU", "UAN", "SAP", "SOW.DE", "PCMI", "PETM" };

	public Stock getStock(String symbol) {
		return null;
	}

	public List<StockHistory> getStockHistory(String symbol, Date from, Date to) {
		return null;
	}

	public List<Stock> getAllStocks() {

		Map<String, Object> urlVariables = new HashMap<>();
		
		// Build the query
		String allSymbols = "";
		for (String symbol : symbols) {
			allSymbols += "\"" + symbol + "\",";
		}
		allSymbols = allSymbols.substring(0, allSymbols.length() - 1);
		String query = "select * from yahoo.finance.quote where symbol in ("
				+ allSymbols + ")";
		
		// Adding necessary variables for the YQL
		urlVariables.put("?q", query);
		urlVariables.put("&format", "json");
		urlVariables.put("&diagnostics", true);
		urlVariables.put("&env", "store://datatables.org/alltableswithkeys");
		
		String uri = serviceUrl;
		for(String key : urlVariables.keySet()) {
			uri += key + "=" + urlVariables.get(key);
		}
		
		// Calling the Service and getting the response
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<YQLQuery> responseEntity = restTemplate.getForEntity(uri, YQLQuery.class);
		
		
		System.out.println(responseEntity.getBody().getResults().toString());
		
		return null;
	}
}
