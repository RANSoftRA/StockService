package service.data;

import service.data.jsonwrapper.YQLQuote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Stock {
	
	private String symbol;
	
	private String name;
	
	private long volume;
	
	private double lastTradedPriceOnly;
	
	private double daysHigh;
	
	private double daysLow;
	
	private String change;
	
	public Stock(YQLQuote quote) {
		symbol = quote.getSymbol();
		name = quote.getName();
		volume = quote.getVolume();
		lastTradedPriceOnly = quote.getLastTradePriceOnly();
		daysHigh = quote.getDaysHigh();
		daysLow = quote.getDaysLow();
		change = quote.getChange();
	}
	
	
	public Stock(String symbol, String name, long volume,
			double lastTradedPriceOnly, double daysHigh, double daysLow,
			String change) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.volume = volume;
		this.lastTradedPriceOnly = lastTradedPriceOnly;
		this.daysHigh = daysHigh;
		this.daysLow = daysLow;
		this.change = change;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public long getVolume() {
		return volume;
	}

	public double getLastTradedPriceOnly() {
		return lastTradedPriceOnly;
	}

	public double getDaysHigh() {
		return daysHigh;
	}

	public double getDaysLow() {
		return daysLow;
	}

	public String getChange() {
		return change;
	}
}
