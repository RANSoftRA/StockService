package service.misc;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import service.data.Stock;
import service.data.StockHistory;

@Component
public class YQL {

	public final String serviceUrlPrefix = "https://query.yahooapis.com/v1/public/yql?q=";
	public final String[] symbols = new String[] { "YHOO", "AAPL", "GOOG", "MSFT", "AGU",
			"UAN", "SAP", "SOW.DE", "PCMI", "PETM" };

	
	public Stock getStock(String symbol) {
		return null;
	}
	
	public List<StockHistory> getStockHistory(String symbol, Date from, Date to) {
		return null;
	}
	
	public List<Stock> getAllStocks() {
		return null;
	}
}
