package com.stockservice.client.data;

public class Stock {
	
	private String symbol;
	
	private String name;
	
	private String volume;
	
	private String lastTradedPriceOnly;
	
	private String daysHigh;
	
	private String daysLow;
	
	private String change;
	
	public Stock(String symbol, String name, String change){
		this.symbol = symbol;
		this.name = name;
		this.change = change;
	}
	
	public Stock(){
	
	}	

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getLastTradedPriceOnly() {
		return lastTradedPriceOnly;
	}

	public void setLastTradedPriceOnly(String lastTradedPriceOnly) {
		this.lastTradedPriceOnly = lastTradedPriceOnly;
	}

	public String getDaysHigh() {
		return daysHigh;
	}

	public void setDaysHigh(String daysHigh) {
		this.daysHigh = daysHigh;
	}

	public String getDaysLow() {
		return daysLow;
	}

	public void setDaysLow(String daysLow) {
		this.daysLow = daysLow;
	}

	public double getChange() {
		return Double.parseDouble(change);
	}

	public void setChange(String change) {
		this.change = change;
	}
	
	
	
	
}
