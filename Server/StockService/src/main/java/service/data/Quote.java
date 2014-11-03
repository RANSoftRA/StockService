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
