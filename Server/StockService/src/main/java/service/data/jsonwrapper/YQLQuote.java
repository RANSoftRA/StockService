package service.data.jsonwrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class YQLQuote {
	
	@JsonProperty("Symbol")
	private String symbol;
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Volume")
	private long volume;
	
	@JsonProperty("LastTradePriceOnly")
	private double lastTradePriceOnly;
	
	@JsonProperty("DaysHigh")
	private double daysHigh;
	
	@JsonProperty("DaysLow")
	private double daysLow;
	
	@JsonProperty("Change")
	private String change;

	@JsonProperty("Date")
	private String date;
	
	@JsonProperty("Close")
	private double close;
	
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

	public double getLastTradePriceOnly() {
		return lastTradePriceOnly;
	}

	public void setLastTradePriceOnly(double lastTradePriceOnly) {
		this.lastTradePriceOnly = lastTradePriceOnly;
	}

	public double getDaysHigh() {
		return daysHigh;
	}

	public void setDaysHigh(double DaysHigh) {
		this.daysHigh = DaysHigh;
	}

	public double getDaysLow() {
		return daysLow;
	}

	public void setDaysLow(double DaysLow) {
		this.daysLow = DaysLow;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}
}