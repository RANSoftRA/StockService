package com.stockservice.client;

import java.util.List;

import com.stockservice.client.data.Stock;

public interface OnLoadDataListener {
	public void onLoadComplete(List<Stock> stockList);
}
