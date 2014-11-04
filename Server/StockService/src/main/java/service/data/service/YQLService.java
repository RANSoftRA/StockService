package service.data.service;

import java.util.List;

import service.data.Stock;
import service.data.StockHistory;

public interface YQLService {

	/**
	 * Stocks that are being supported
	 */
	final String[] symbols = new String[] { "YHOO", "AAPL", "GOOG", "MSFT",
			"AGU", "UAN", "SAP", "SOW.DE", "PCMI", "PETM" };

	/**
	 * Getting the current Stock from YAHOO
	 * 
	 * @param stockSymbol
	 *            Stock Symbol (ID)
	 * @return The current Stock Object from YAHOO
	 */
	Stock getStock(String stockSymbol);

	/**
	 * Gets the History of a specific Stock
	 * 
	 * @param stockSymbol
	 *            Which Stock
	 * @param from
	 *            Starting at
	 * @param to
	 *            Ending at
	 * @return List of StockHistory Objects, where the value of each day is
	 *         stored
	 */
	List<StockHistory> getStockHistory(String stockSymbol, String from,
			String to);

	/**
	 * Getting all available Stocks (YQLService.symbols)
	 * 
	 * @return List of available Stocks
	 */
	List<Stock> getAllStocks();

	/**
	 * Getting the current price of a Stock from YAHOO
	 * 
	 * @param stockSymbol
	 *            Which Stock
	 * @return The current price of the given stock
	 */
	double getCurrentPrice(String stockSymbol);
	
	/**
	 * Method for determining, if the given stock is supported
	 * @param symbol
	 * Which Stock
	 * @return
	 * true - is supported; false otherwise
	 */
	boolean isSymbolSupported(String symbol);
}
