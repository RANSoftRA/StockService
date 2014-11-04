package service.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Quote {
	private String symbol;
	
	private String name;
	
	private long volume;
	
	private double lastTradedPriceOnly;
	
	private double daysHigh;
	
	private double daysLow;
	
	private double change;

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

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public double getLastTradedPriceOnly() {
		return lastTradedPriceOnly;
	}

	public void setLastTradedPriceOnly(double lastTradedPriceOnly) {
		this.lastTradedPriceOnly = lastTradedPriceOnly;
	}

	public double getDaysHigh() {
		return daysHigh;
	}

	public void setDaysHigh(double daysHigh) {
		this.daysHigh = daysHigh;
	}

	public double getDaysLow() {
		return daysLow;
	}

	public void setDaysLow(double daysLow) {
		this.daysLow = daysLow;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}
	
}