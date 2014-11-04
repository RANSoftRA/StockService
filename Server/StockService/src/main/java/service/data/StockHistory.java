package service.data;

import java.util.Date;

public class StockHistory {
	private String symbol;
	
	private Date date;
	
	private double close;
	
	public StockHistory(String symbol, Date date, double close) {
		super();
		this.symbol = symbol;
		this.date = date;
		this.close = close;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}
	
	
}
