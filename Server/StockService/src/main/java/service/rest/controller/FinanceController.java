package service.rest.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.data.Stock;
import service.data.StockHistory;
import service.data.TransactionResponse;
import service.data.service.YQLService;
import service.misc.DateFormat;
import service.persistence.TransactionDao;
import service.persistence.domain.Transaction;

@RestController
@RequestMapping("/finance")
public class FinanceController {

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private YQLService yql;

	@Autowired
	private DateFormat dateFormat;


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

	@RequestMapping(value = "/stocks/{symbol}", method = RequestMethod.POST)
	@Transactional
	public TransactionResponse buyStock(
			@RequestParam(value = "sessionID", required = true) String sessionId,
			@PathVariable String symbol,
			@RequestParam(value = "amount", required = true) int amount) {
		
		// TODO Current User Parameter (currently null)
		transactionDao.saveOrUpdateTransaction(new Transaction(symbol, amount,
				new Date(), yql.getCurrentPrice(symbol),
				Transaction.TransactionType.BUY, null));
		
		return new TransactionResponse(null, null);
	}

	@RequestMapping(value = "/stocks/{symbol}", method = RequestMethod.DELETE)
	@Transactional
	public TransactionResponse sellStock(
			@RequestParam(value = "sessionID", required = true) String sessionId,
			@PathVariable String symbol,
			@RequestParam(value = "amount", required = true) int amount) {

		// TODO Current User Parameter (currently null)
		transactionDao.saveOrUpdateTransaction(new Transaction(symbol, amount,
				new Date(), yql.getCurrentPrice(symbol),
				Transaction.TransactionType.SELL, null));

		return new TransactionResponse(null, null);

	}

}
