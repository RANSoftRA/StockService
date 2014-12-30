package com.stockservice.client.data;

import java.util.ArrayList;

public class DummyDataGenerator {
	
	private ArrayList<Stock> stocks;
	private ArrayList<StockHistory> history;
	private String historyText;
	
	public DummyDataGenerator(){
		genDummyStocks();
		genDummyStockHistoryAsList();
		genDummyStockHistoryAsText();
	}
	
	private void genDummyStockHistoryAsList(){
		history = new ArrayList<StockHistory>();
		for(int i = 1;i<=5;i++){
    		history.add(new StockHistory("DUMMY","0"+i+".01.2014","5"+i));
    	}
	}
	
	public void genDummyStockHistoryAsText(){
		for(StockHistory s:history){
			historyText = historyText + System.getProperty ("line.separator")+s.getSymbol()+" "+s.getDate()+" "+s.getValue();
		}	
	}
	
	public void genDummyStocks(){
		stocks = new ArrayList<Stock>();
    	for(int i = 1;i<=5;i++){
    		stocks.add(new Stock("STO"+i,"Stock "+i,"0.0"+i));
    	}	
	}

	public ArrayList<Stock> getStocks() {
		return stocks;
	}

	public ArrayList<StockHistory> getHistory() {
		return history;
	}

	public String getHistoryText() {
		return historyText;
	}	

}
