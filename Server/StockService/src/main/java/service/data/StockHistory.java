package service.data;

import service.data.jsonwrapper.YQLQuote;


public class StockHistory {
	private String symbol;
	
	private String date;
	
	private double value;
	
	public StockHistory(String symbol, String date, double value) {
		super();
		this.symbol = symbol;
		this.date = date;
		this.value = value;
	}
	
	public StockHistory(YQLQuote quote) {
		symbol = quote.getSymbol();
		date = quote.getDate();
		value = quote.getClose();
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	
}
