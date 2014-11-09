package service.rest.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.data.Stock;
import service.data.StockHistory;
import service.data.service.YQLService;
import service.misc.DateFormat;
import service.persistence.TransactionDao;

@RestController
@RequestMapping("/finance")
public class FinanceController {

	@Autowired
	private YQLService yql;

	@Autowired
	private DateFormat dateFormat;
	
	@Autowired
	private TransactionDao transactionDao;


	@RequestMapping(value = "/stocks", method = RequestMethod.GET)
	public List<Stock> getStocks() {
		return yql.getAllStocks();
	}

	@RequestMapping(value = "/stocks/{symbol}/history", method = RequestMethod.GET)
	public List<StockHistory> getStockHistory(@PathVariable String symbol) {
		Date now = new Date();
		Calendar fromCal = Calendar.getInstance();
		fromCal.setTime(now);
		fromCal.add(Calendar.MONTH, -1);
		String dateFrom = dateFormat.format(fromCal.getTime());
		String dateTo = dateFormat.format(now);
		return yql.getStockHistory(symbol, dateFrom, dateTo);
	}
}
