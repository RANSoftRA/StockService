package com.stockservice.client.data;

public class StockHistory {
	private String symbol;
	
	private String date;
	
	private String value;
	
	public StockHistory(String symbol, String date, String value) {
		super();
		this.symbol = symbol;
		this.date = date;
		this.value = value;
	}
	
	public StockHistory() {
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
		return Double.parseDouble(value);
	}

	public void setValue(double value) {
		this.value = String.valueOf(value);
	}
	
	
}
