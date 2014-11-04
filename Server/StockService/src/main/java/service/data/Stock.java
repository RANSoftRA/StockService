package service.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Stock {
	
	private String symbol;
	
	private String name;
	
	private long volume;
	
	private double lastTradedPriceOnly;
	
	private double daysHigh;
	
	private double daysLow;
	
	private double change;
	
	
	public Stock(String symbol, String name, long volume,
			double lastTradedPriceOnly, double daysHigh, double daysLow,
			double change) {
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

	public double getChange() {
		return change;
	}
}
